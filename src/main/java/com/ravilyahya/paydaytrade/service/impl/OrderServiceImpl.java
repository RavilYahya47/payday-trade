package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.dao.request.ReqOrder;
import com.ravilyahya.paydaytrade.dao.response.RespOrder;
import com.ravilyahya.paydaytrade.model.Order;
import com.ravilyahya.paydaytrade.model.OrderType;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.repository.OrderRepository;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import com.ravilyahya.paydaytrade.service.OrderService;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OrderExecutionService orderExecutionService;

    @Override
    public RespOrder placeBuyOrder(ReqOrder respOrder) throws Exception {

        User user = userRepository.findByUsername(respOrder.getUsername());
        Order order = new Order();
        order.setUser(user);
        order.setTargetPrice(respOrder.getTargetPrice());
        order.setStockSymbol(respOrder.getStockSymbol());
        order.setAmount(respOrder.getAmount());
        order.setOrderType(OrderType.BUY);


        orderRepository.save(order);
        log.info("User " + user.getUsername()+ " have placed BUY order");
        log.info(order.toString());

        return orderExecutionService.processBuyOrder(order);


    }

    @Override
    public RespOrder placeSellOrder(ReqOrder respOrder) throws Exception {
        User user = userRepository.findByUsername(respOrder.getUsername());
        Order order = new Order();
        order.setUser(user);
        order.setTargetPrice(respOrder.getTargetPrice());
        order.setStockSymbol(respOrder.getStockSymbol());
        order.setAmount(respOrder.getAmount());
        order.setOrderType(OrderType.SELL);
        orderRepository.save(order);

        log.info("User " + user.getUsername()+ " have placed SELL order");
        log.info(order.toString());

        return orderExecutionService.processSellOrder(order);
    }









    @Override
    public List<Order> listAllOrders(String username) throws Exception {
        User user = userRepository.findByUsername(username);
        return orderRepository.getOrdersByUserAndIsActive(user,true);
    }
}
