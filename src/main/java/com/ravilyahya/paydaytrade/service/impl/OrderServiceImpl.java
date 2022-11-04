package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.repository.OrderRepository;
import com.ravilyahya.paydaytrade.service.OrderService;
import com.ravilyahya.paydaytrade.service.StockService;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;

    @Override
    public void placeBuyOrder(String username, OrderType orderType, String stockSymbol, Double amount, BigDecimal targetPrice) throws Exception {
        placeOrder( username, OrderType.BUY,  stockSymbol,  amount, targetPrice);
    }

    @Override
    public void placeSellOrder(String username, OrderType orderType, String stockSymbol, Double amount, BigDecimal targetPrice) throws Exception {
        placeOrder( username, OrderType.SELL,  stockSymbol,  amount, targetPrice);
    }



    private void placeOrder(String username, OrderType orderType, String stockSymbol, Double amount, BigDecimal targetPrice) throws Exception {
        User user = userService.getUserByUsername(username);
        Order order = new Order();
        order.setUser(user);
        order.setTargetPrice(targetPrice);
        order.setStockSymbol(stockSymbol);
        order.setAmount(amount);
        if(orderType.equals(OrderType.BUY)){
            order.setOrderType(OrderType.BUY);
        } else if (orderType.equals(OrderType.SELL)) {
            order.setOrderType(OrderType.SELL);
        }

        //TODO  call to Order execution service

        orderRepository.save(order);
    }



    @Override
    public List<Order> listAllOrders(String username) throws Exception {
        User user = userService.getUserByUsername(username);
        return orderRepository.getOrdersByUser(user);
    }
}
