package com.ravilyahya.paydaytrade.dao.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ReqJwt {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Size(min = 6,max = 20 , message = "Password length must be between 6 and 20 characters")
    private String password;

    public ReqJwt(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
