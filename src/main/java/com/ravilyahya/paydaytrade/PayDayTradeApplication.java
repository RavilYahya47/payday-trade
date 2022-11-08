package com.ravilyahya.paydaytrade;

import com.ravilyahya.paydaytrade.service.OrderExecutionService;
import com.ravilyahya.paydaytrade.service.impl.OrderExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class PayDayTradeApplication  implements CommandLineRunner {
    private final OrderExecutionService orderExecutionService;
    private final OrderExecutor orderExecutor;

    public static void main(String[] args) {
        SpringApplication.run(PayDayTradeApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        orderExecutor.executeOrders();
    }
}
