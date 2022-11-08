package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.model.StockWrapper;
import com.ravilyahya.paydaytrade.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class StockServiceImplTest {

    @Autowired
    private  StockService stockService;

    @Test
    void invoke(){
        final StockWrapper stock = stockService.findStock("UU.L");
        System.out.println(stock.getStock());
    }

    @Test
    void multipleStocks(){
        final List<StockWrapper> stocks = stockService.findStocks();
        findPrices(stocks);
    }

    private void findPrices(List<StockWrapper> stocks){
        stocks.forEach(stock -> {
            System.out.println(stock.getStock());
        });
    }

}