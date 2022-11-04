package com.ravilyahya.paydaytrade.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private OrderType orderType;
    private String stockSymbol;
    private Double amount;
    private BigDecimal targetPrice;
    private Boolean isExecuted=false;
    private Boolean isActive=true;
}
