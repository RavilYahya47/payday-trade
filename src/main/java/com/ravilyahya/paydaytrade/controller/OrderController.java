package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.request.ReqOrder;
import com.ravilyahya.paydaytrade.dao.response.RespOrder;
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
    public ResponseEntity<RespOrder> placeBuyOrder(@RequestBody ReqOrder reqOrder) throws Exception {
        return ResponseEntity.ok(orderService.placeBuyOrder(reqOrder));
    }

    @PostMapping("/sell")
    public ResponseEntity<RespOrder> placeSellOrder(@RequestBody ReqOrder reqOrder) throws Exception {
        return ResponseEntity.ok(orderService.placeSellOrder(reqOrder));
    }
}
