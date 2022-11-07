package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.dao.request.ReqOrder;
import com.ravilyahya.paydaytrade.dao.response.RespOrder;
import com.ravilyahya.paydaytrade.model.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    RespOrder placeBuyOrder(ReqOrder reqOrder) throws Exception;
    RespOrder placeSellOrder(ReqOrder respOrder) throws Exception;
    List<Order> listAllOrders(String username) throws Exception;


}
