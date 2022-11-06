package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.exception.BalanceIsNotEnoughException;
import com.ravilyahya.paydaytrade.exception.OrderTargetPriceDoesntMatchException;
import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.stock.StockWrapper;
import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderExecutionServiceImpl implements OrderExecutionService {
    private final StockService stockService;

    Boolean processBuyOrder(Order order) throws BalanceIsNotEnoughException, OrderTargetPriceDoesntMatchException {
        Stock stock = stockService.findStock(order.getStockSymbol()).getStock();
        User user = order.getUser();


        BigDecimal requiredAmountOfMoney = order.getAmount().multiply(order.getTargetPrice());


        //If user don't have enough balance
        if(order.getOrderType().equals(OrderType.BUY) && user.getBalance().compareTo(requiredAmountOfMoney)<0){
            throw new BalanceIsNotEnoughException("You don't have enough balance to place this operation");
        }

        //If stock price didn't meet order target price
        if(stock.getQuote().getPrice().compareTo(order.getTargetPrice())<0){
            throw new OrderTargetPriceDoesntMatchException(stock.getSymbol() + " price is higher than your BUY bid!");
        }

        return true;





    }

}
