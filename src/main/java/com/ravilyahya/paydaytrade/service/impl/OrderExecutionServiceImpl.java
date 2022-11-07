package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.dao.response.RespOrder;
import com.ravilyahya.paydaytrade.exception.BalanceIsNotEnoughException;
import com.ravilyahya.paydaytrade.exception.OrderTargetPriceDoesntMatchException;
import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.repository.OrderRepository;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yahoofinance.Stock;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
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
            throw new BalanceIsNotEnoughException("You don't have enough balance to place this operation");
        }

        //If stock price didn't meet order target price
        if(stock.getQuote().getPrice().compareTo(order.getTargetPrice()) > 0){
            throw new OrderTargetPriceDoesntMatchException(stock.getSymbol() + " price is higher than your BUY bid!");
        }

        // execute order
        user.setBalance(user.getBalance().subtract(requiredAmountOfMoney));
        order.setIsExecuted(true);
        order.setIsActive(false);

        Order savedOrder = orderRepository.save(order);
        userRepository.save(user);

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
            throw new OrderTargetPriceDoesntMatchException(stock.getSymbol() + " price is higher than your BUY bid!");
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


        return respOrder;
    }
}
