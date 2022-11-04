package com.ravilyahya.paydaytrade.controller;

import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.UserRole;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setId(2L);
        role.setName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User savedUser = userService.createUser(user,userRoles);
        return savedUser;
    }
}
