package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.dao.response.RespStock;
import com.ravilyahya.paydaytrade.model.StockWrapper;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface StockService {
     StockWrapper findStock(String ticker);
     BigDecimal getPrice(final StockWrapper stockWrapper) throws IOException;
     List<StockWrapper> findStocks();

     ResponseEntity<List<RespStock>> getAllStocks();
}
