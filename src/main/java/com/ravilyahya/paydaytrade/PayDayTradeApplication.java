package com.ravilyahya.paydaytrade;

import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.UserRole;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class PayDayTradeApplication   {

    public static void main(String[] args) {
        SpringApplication.run(PayDayTradeApplication.class, args);
    }




}
