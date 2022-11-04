package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.stock.StockWrapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface StockService {
     StockWrapper findStock(String ticker);
     BigDecimal getPrice(final StockWrapper stockWrapper) throws IOException;
     List<StockWrapper> findStocks(final List<String> tickers);
}
