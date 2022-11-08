package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.dao.request.ReqJwt;
import com.ravilyahya.paydaytrade.dao.response.RespJwt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthenticationService {
    ResponseEntity<RespJwt> login(@RequestBody @Valid ReqJwt reqJwt) throws Exception;
}
