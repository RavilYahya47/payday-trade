package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.dao.response.RespOrder;
import com.ravilyahya.paydaytrade.exception.BalanceIsNotEnoughException;
import com.ravilyahya.paydaytrade.exception.OrderTargetPriceDoesntMatchException;
import com.ravilyahya.paydaytrade.model.Order;

public interface OrderExecutionService {

    RespOrder processBuyOrder(Order order) throws BalanceIsNotEnoughException, OrderTargetPriceDoesntMatchException;

    RespOrder processSellOrder(Order order) throws OrderTargetPriceDoesntMatchException;

    void processAllWaitingBuyOrders();
    void processAllWaitingSellOrders();

}
