package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.StockResponse;
import com.ravilyahya.paydaytrade.model.stock.StockWrapper;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @Value("#{'${listOfStockNames}'.split(',')}")
    private List<String> stockSymbols;

    @GetMapping("/list-stocks")
    public ResponseEntity<?> getAllStocks(){

        List<StockWrapper> stocks = stockService.findStocks(stockSymbols);
        List<StockResponse> responseList = new ArrayList<>();

        for(StockWrapper stock:stocks){
            responseList.add(new StockResponse(stock.getStock().getSymbol(),stock.getStock().getQuote().getPrice()));
        }

        return ResponseEntity.ok(responseList);
    }
}
