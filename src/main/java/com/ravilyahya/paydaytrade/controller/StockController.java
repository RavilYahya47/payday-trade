package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.response.RespStock;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @GetMapping("/list-stocks")
    public ResponseEntity<List<RespStock>> getAllStocks() {
        return stockService.getAllStocks();
    }
}
