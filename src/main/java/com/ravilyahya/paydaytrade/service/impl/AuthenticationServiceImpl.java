package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.exception.UserNotFoundException;
import com.ravilyahya.paydaytrade.dao.request.ReqJwt;
import com.ravilyahya.paydaytrade.dao.response.RespJwt;
import com.ravilyahya.paydaytrade.security.JwtUtils;
import com.ravilyahya.paydaytrade.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    public ResponseEntity<RespJwt> login(@RequestBody @Valid ReqJwt reqJwt) throws Exception {
        try {
            authenticate(reqJwt.getUsername(), reqJwt.getPassword());

        }catch (UserNotFoundException exception){
            throw new Exception("User not found!");
        }

        UserDetails user =  userDetailsService.loadUserByUsername(reqJwt.getUsername());
        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new RespJwt(token));
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
