package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.dao.request.ReqSingUpUser;
import com.ravilyahya.paydaytrade.dao.response.RespUser;
import com.ravilyahya.paydaytrade.exception.UserFoundException;
import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.UserRole;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.repository.RoleRepository;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RespUser createNormalUser(ReqSingUpUser reqSingUpUser) {
        User user = userRepository.findByUsername(reqSingUpUser.getUsername());

        if (user != null) {
            System.out.println("User is already exists!");
            throw new UserFoundException();
        }

        user = new User();

        user.setUsername(reqSingUpUser.getUsername());
        user.setEmail(reqSingUpUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(reqSingUpUser.getPassword()));

        Role role = new Role();
        role.setId(2L);
        role.setName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);

        user = userRepository.save(user);

        return modelMapper.map(user, RespUser.class);
    }

    @Override
    public RespUser getUserByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username), RespUser.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public RespUser depositCash(String username,BigDecimal amount) {
        User user = userRepository.findByUsername(username);
        user.setBalance(user.getBalance().add(amount));
        return modelMapper.map(userRepository.save(user),RespUser.class);
    }


}
