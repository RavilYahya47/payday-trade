package com.ravilyahya.paydaytrade.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserVerificationService {
    ResponseEntity<Map<String,String>> verifyUser(String username, String activationCode);
}
