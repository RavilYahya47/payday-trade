package com.ravilyahya.paydaytrade.dao.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ReqOrder {

    private String username;
    private String orderType;
    private String stockSymbol;
    private BigDecimal amount;
    private BigDecimal targetPrice;
}
