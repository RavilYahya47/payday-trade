package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.request.ReqSingUpUser;

import com.ravilyahya.paydaytrade.dao.response.RespUser;
import com.ravilyahya.paydaytrade.service.UserService;
import com.ravilyahya.paydaytrade.service.UserVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserVerificationService userVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<RespUser> createUser(@RequestBody @Valid ReqSingUpUser user) throws Exception {
        return ResponseEntity.ok(userService.createNormalUser(user));
    }

    @GetMapping("/verify/{username}/{activationCode}")
    public ResponseEntity<Map<String,String>> verifyUser(@PathVariable String username, @PathVariable String activationCode){
        return userVerificationService.verifyUser(username,activationCode);
    }
}
