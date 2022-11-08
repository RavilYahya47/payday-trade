package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.dao.request.ReqJwt;
import com.ravilyahya.paydaytrade.dao.response.RespJwt;
import com.ravilyahya.paydaytrade.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationServiceImpl;


    @PostMapping("/api/login")
    public ResponseEntity<RespJwt> login(@RequestBody @Valid ReqJwt reqJwt) throws Exception {
        ResponseEntity<RespJwt> response = authenticationServiceImpl.login(reqJwt);
        log.info(String.valueOf(response));
        return response;
    }
}
