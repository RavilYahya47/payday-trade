package com.ravilyahya.paydaytrade.dao.request;

import lombok.Data;

@Data
public class ReqSingUpUser {
    private String username;
    private String email;
    private String password;
}
