package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class OrderExecutor  {
    private final OrderExecutionService orderExecutionService;

    Runnable orderRunnable = new Runnable() {
        public void run() {
            orderExecutionService.processAllWaitingBuyOrders();
            orderExecutionService.processAllWaitingSellOrders();
        }
    };

    public void executeOrders(){
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(orderRunnable, 0, 1, TimeUnit.MINUTES);
    }


}
