package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.dao.response.RespOrder;
import com.ravilyahya.paydaytrade.exception.BalanceIsNotEnoughException;
import com.ravilyahya.paydaytrade.exception.OrderTargetPriceDoesntMatchException;
import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;
import com.ravilyahya.paydaytrade.model.StockWrapper;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.repository.OrderRepository;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderExecutionServiceImpl implements OrderExecutionService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StockService stockService;
    private final ModelMapper modelMapper;
    @Transactional
    public RespOrder processBuyOrder(Order order) throws BalanceIsNotEnoughException, OrderTargetPriceDoesntMatchException {
        Stock stock = stockService.findStock(order.getStockSymbol()).getStock();
        User user = order.getUser();

        BigDecimal requiredAmountOfMoney = order.getAmount().multiply(order.getTargetPrice());


        //If user don't have enough balance
        if(order.getOrderType().equals(OrderType.BUY) && user.getBalance().compareTo(requiredAmountOfMoney)<0){
            log.warn("User: "+ user.getUsername() + " don't have enough balance to process order.");
            log.warn(order.toString());
            throw new BalanceIsNotEnoughException("You don't have enough balance to place this operation");
        }

        //If stock price didn't meet order target price
        if(stock.getQuote().getPrice().compareTo(order.getTargetPrice()) > 0){
            log.warn(stock.getSymbol() + " price is higher than "+user.getUsername() +"'s BUY bid!");
            log.warn(order.toString());
            log.warn("Order have been added to waiting pool");

            //TODO add order to waiting pool

            throw new OrderTargetPriceDoesntMatchException(stock.getSymbol() + " price is higher than your BUY bid!");
        }

        // execute order
        user.setBalance(user.getBalance().subtract(requiredAmountOfMoney));
        order.setIsExecuted(true);
        order.setIsActive(false);

        Order savedOrder = orderRepository.save(order);
        userRepository.save(user);

        log.info(user.getUsername()+"'s order has been executed\n" + order);

        RespOrder respOrder = modelMapper.map(order, RespOrder.class);
        respOrder.setUsername(user.getUsername());
        respOrder.setId(user.getId());


        return respOrder;

    }
    public RespOrder processSellOrder(Order order) throws OrderTargetPriceDoesntMatchException{
        Stock stock = stockService.findStock(order.getStockSymbol()).getStock();
        User user = order.getUser();



        //If stock price didn't meet order target price
        if(order.getTargetPrice().compareTo(stock.getQuote().getPrice()) > 0){
            log.warn(stock.getSymbol() + " price is lower than "+user.getUsername() +"'s SELL bid!");
            log.warn(order.toString());
            log.warn("Order have been added to waiting pool");
            throw new OrderTargetPriceDoesntMatchException(stock.getSymbol() + " price is higher than your SELL bid!");
        }

        // execute order
        user.setBalance(user.getBalance().add(order.getTargetPrice().multiply(order.getAmount())));
        order.setIsExecuted(true);
        order.setIsActive(false);

        Order savedOrder = orderRepository.save(order);
        userRepository.save(user);

        RespOrder respOrder = modelMapper.map(order, RespOrder.class);
        respOrder.setUsername(user.getUsername());
        respOrder.setId(user.getId());
        log.info(user.getUsername()+"'s order has been executed\n" + order);


        return respOrder;
    }

    public void processAllWaitingBuyOrders() {
        List<Order> orders = orderRepository.getOrderByOrderTypeAndIsExecuted(OrderType.BUY,false);
        Map<String,BigDecimal> stocksMap = new HashMap<>();
        stockService.findStocks().forEach(stockWrapper -> stocksMap.put(stockWrapper.getStock().getSymbol(),stockWrapper.getStock().getQuote().getPrice()));

        orders.forEach(order -> processWaitingBuyOrder(order,stocksMap.get(order.getStockSymbol())));
    }
    private void processWaitingBuyOrder(Order order, BigDecimal orderStockPrice){
        BigDecimal requiredAmountOfMoney = order.getAmount().multiply(order.getTargetPrice());
        User user = order.getUser();


        if(!(user.getBalance().compareTo(requiredAmountOfMoney)<0) && !(order.getTargetPrice().compareTo(orderStockPrice)<0))
        {
            System.out.println("Processing BUY order: " + order.getId());
//            user.setBalance(user.getBalance().subtract(requiredAmountOfMoney));
//            order.setIsActive(false);
//            order.setIsExecuted(true);
//            userRepository.save(user);
//            orderRepository.save(order);
        }

    }

    public void processAllWaitingSellOrders(){
        List<Order> orders = orderRepository.getOrderByOrderTypeAndIsExecuted(OrderType.SELL,false);
        Map<String,BigDecimal> stocksMap = new HashMap<>();
        stockService.findStocks().forEach(stockWrapper -> stocksMap.put(stockWrapper.getStock().getSymbol(),stockWrapper.getStock().getQuote().getPrice()));

        orders.forEach(order -> processWaitingSellOrder(order,stocksMap.get(order.getStockSymbol())));

    }

    private void processWaitingSellOrder(Order order, BigDecimal orderStockPrice){
        BigDecimal requiredAmountOfMoney = order.getAmount().multiply(order.getTargetPrice());
        User user = order.getUser();
        if( !(orderStockPrice.compareTo(order.getTargetPrice())>0))
        {
            System.out.println("Processing SELL order: " + order.getId());
//            user.setBalance(user.getBalance().add(requiredAmountOfMoney));
//            order.setIsActive(false);
//            order.setIsExecuted(true);
//            userRepository.save(user);
//            orderRepository.save(order);
        }

    }
}
