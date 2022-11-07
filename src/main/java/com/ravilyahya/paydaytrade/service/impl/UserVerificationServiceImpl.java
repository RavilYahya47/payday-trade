package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.exception.UserNotFoundException;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.service.UserVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserVerificationServiceImpl implements UserVerificationService {
    private final UserRepository userRepository;
    public ResponseEntity<Map<String,String>> verifyUser(String username, String activationCode){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UserNotFoundException("User with %s username haven't found in db");
        }

        if(user.getActivationCode().equals(activationCode)){
            userRepository.enableAppUser(username);
            log.info("User has been activated: " + user.getUsername());
            return ResponseEntity.ok(Map.of("success",user.getUsername() + "your account have successfully been activated!"));
        }

        log.warn("Error occurred during account activation for user: " + user.getUsername());

        return ResponseEntity.ok(Map.of("error","Your activation code is invalid!"));
    }
}
