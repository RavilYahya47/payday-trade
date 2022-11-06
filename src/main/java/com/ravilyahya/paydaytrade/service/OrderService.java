package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    void placeBuyOrder(String username,  String stockSymbol, BigDecimal amount, BigDecimal targetPrice) throws Exception;
    void placeSellOrder(String username,  String stockSymbol, BigDecimal amount, BigDecimal targetPrice) throws Exception;
    List<Order> listAllOrders(String username) throws Exception;


}
