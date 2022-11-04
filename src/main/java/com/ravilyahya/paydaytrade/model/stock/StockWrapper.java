package com.ravilyahya.paydaytrade.model.stock;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@AllArgsConstructor
public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockWrapper(Stock stock){
        this.stock=stock;
        lastAccessed=LocalDateTime.now();
    }
}
