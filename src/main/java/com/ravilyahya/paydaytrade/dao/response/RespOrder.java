package com.ravilyahya.paydaytrade.dao.response;

import com.ravilyahya.paydaytrade.model.OrderType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class RespOrder {

    private Long id;
    private String username;
    private String orderType;
    private String stockSymbol;
    private BigDecimal amount;
    private BigDecimal targetPrice;
    private Boolean isExecuted;
}
