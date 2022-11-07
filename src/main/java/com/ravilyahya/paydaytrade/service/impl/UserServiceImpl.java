package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.dao.request.ReqSingUpUser;
import com.ravilyahya.paydaytrade.dao.response.RespUser;
import com.ravilyahya.paydaytrade.exception.UserFoundException;
import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.UserRole;
import com.ravilyahya.paydaytrade.repository.UserRepository;
import com.ravilyahya.paydaytrade.repository.RoleRepository;
import com.ravilyahya.paydaytrade.service.EmailSenderService;
import com.ravilyahya.paydaytrade.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSenderService emailSenderService;

    public RespUser createNormalUser(ReqSingUpUser reqSingUpUser) throws MessagingException {
        User user = userRepository.findByUsername(reqSingUpUser.getUsername());

        if (user != null) {
            String message=String.format("Username: %s has been taken ! Try to get another username!",user.getUsername());
            log.warn(message);
            throw new UserFoundException(message);
        }

        user = new User();

        user.setUsername(reqSingUpUser.getUsername());
        user.setEmail(reqSingUpUser.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(reqSingUpUser.getPassword()));
        user.setActivationCode(String.valueOf(UUID.randomUUID()));

        Role role = new Role();
        role.setId(2L);
        role.setName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setRole(role);
        userRole.setUser(user);

        user.getUserRoles().add(userRole);

        user = userRepository.save(user);
        emailSenderService.sendActivationEmail(user);
        log.info("User registered: " + user.getUsername());
        log.info(String.format("Activation email sent to %s for user: %s",user.getEmail(),user.getUsername()));

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
