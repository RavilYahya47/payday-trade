package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.request.ReqSingUpUser;

import com.ravilyahya.paydaytrade.dao.response.RespUser;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<RespUser> createUser(@RequestBody ReqSingUpUser user) throws Exception {
        return ResponseEntity.ok(userService.createNormalUser(user));
    }
}
