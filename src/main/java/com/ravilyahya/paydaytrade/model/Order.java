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
    private BigDecimal amount;
    private BigDecimal targetPrice;
    private Boolean isExecuted=false;
    private Boolean isActive=true;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", orderType=" + orderType +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", amount=" + amount +
                ", targetPrice=" + targetPrice +
                ", isExecuted=" + isExecuted +
                ", isActive=" + isActive +
                '}';
    }
}
