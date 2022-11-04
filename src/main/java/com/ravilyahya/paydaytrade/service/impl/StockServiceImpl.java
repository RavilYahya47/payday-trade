package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.model.stock.StockWrapper;
import com.ravilyahya.paydaytrade.service.StockRefreshService;
import com.ravilyahya.paydaytrade.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRefreshService refreshService;

    public StockWrapper findStock(String ticker){
        try{
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
            throw new RuntimeException("Error occurred during  getting stock");
        }
    }

    public List<StockWrapper> findStocks(final List<String> tickers){
        return tickers.stream().map(this::findStock).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public BigDecimal getPrice(final StockWrapper stockWrapper) throws IOException {
        return stockWrapper.getStock().getQuote(refreshService.shouldRefresh(stockWrapper)).getPrice();
    }
}
