package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.RespOrder;
import com.ravilyahya.paydaytrade.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/buy")
    public ResponseEntity<?> placeOrder(@RequestBody RespOrder respOrder) throws Exception {

        if(respOrder.getOrderType().equalsIgnoreCase("BUY")){
            orderService.placeBuyOrder(respOrder.getUsername(),respOrder.getStockSymbol(),respOrder.getAmount(),respOrder.getTargetPrice());
        }else if (respOrder.getOrderType().equalsIgnoreCase("SELL")){
            orderService.placeSellOrder(respOrder.getUsername(),respOrder.getStockSymbol(),respOrder.getAmount(),respOrder.getTargetPrice());
        }

        return ResponseEntity.ok(orderService.listAllOrders(respOrder.getUsername()));
    }
}
