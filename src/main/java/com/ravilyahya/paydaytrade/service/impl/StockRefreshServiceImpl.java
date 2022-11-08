package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.model.StockWrapper;
import com.ravilyahya.paydaytrade.service.StockRefreshService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Service
public class StockRefreshServiceImpl implements StockRefreshService {
    private final Map<StockWrapper, Boolean> stocksToRefresh;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final Duration refreshPeriod = Duration.ofSeconds(15);

    public StockRefreshServiceImpl() {
        stocksToRefresh = new HashMap<>();
        setRefreshEvery15Seconds();
    }

    public Boolean shouldRefresh(final StockWrapper stock){
        if(!stocksToRefresh.containsKey(stock)){
            stocksToRefresh.put(stock,true);
            return true;
        }
        return stocksToRefresh.get(stock);
    }

    private void setRefreshEvery15Seconds() {
        scheduler.scheduleAtFixedRate(
                () -> stocksToRefresh.forEach((stock, value) -> {
                    if (stock.getLastAccessed().isBefore(LocalDateTime.now().minus(refreshPeriod))) {
                        System.out.println("Setting should refresh " + stock.getStock().getSymbol());
                        stocksToRefresh.remove(stock);
                        stocksToRefresh.put(stock.withLastAccessed(LocalDateTime.now()), true);
                    }
                }), 0, 15, SECONDS);
    }
}
