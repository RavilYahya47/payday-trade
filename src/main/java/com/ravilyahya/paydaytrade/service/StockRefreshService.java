package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.stock.StockWrapper;

public interface StockRefreshService {
     Boolean shouldRefresh(final StockWrapper stock);
}
