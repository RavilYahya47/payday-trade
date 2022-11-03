package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements UserDetailsService {
    private final static String USER_NOT_FOUND="User with username %s not found";
    private final AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->new UsernameNotFoundException(String.format(USER_NOT_FOUND,username)));
    }

    public int enableAppUser(String username) {
        return userRepository.enableAppUser(username);
    }
}
