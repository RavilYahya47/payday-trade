package com.ravilyahya.paydaytrade.dao.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ReqSingUpUser {

    @NotBlank(message = "Username must not be blank")
    private String username;

    @Email(message = "You must provide valid email")
    private String email;

    @Size(min = 6,max = 20 , message = "Password length must be between 6 and 20 characters")
    private String password;
}
