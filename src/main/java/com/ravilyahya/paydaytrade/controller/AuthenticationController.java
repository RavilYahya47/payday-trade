package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.exception.UserNotFoundException;
import com.ravilyahya.paydaytrade.model.jwt.JwtRequest;
import com.ravilyahya.paydaytrade.model.jwt.JwtResponse;
import com.ravilyahya.paydaytrade.security.JwtUtils;
import com.ravilyahya.paydaytrade.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;


    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());

        }catch (UserNotFoundException exception){
            throw new Exception("User not found!");
        }

        UserDetails user =  userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        System.out.println(username);
        System.out.println(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw new Exception("User Disabled!");
        }catch (BadCredentialsException exception){
            throw new Exception("Invalid credentials: " + exception.getMessage());
        }
    }
}
