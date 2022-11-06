package com.ravilyahya.paydaytrade.dao.response;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class RespUser {

    private Long id;
    private String username;
    private String email;
    private BigDecimal balance;

    @Override
    public String toString() {
        return "RespUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
