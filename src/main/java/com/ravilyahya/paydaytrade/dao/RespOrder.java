package com.ravilyahya.paydaytrade.dao;

import com.ravilyahya.paydaytrade.model.OrderType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class RespOrder {

    private String username;
    private String orderType;
    private String stockSymbol;
    private BigDecimal amount;
    private BigDecimal targetPrice;
}
