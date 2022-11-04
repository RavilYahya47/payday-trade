package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.UserRole;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public interface UserService {
     User createUser(User user, Set<UserRole> userRoles) throws Exception;

     User  getUserByUsername (String username) throws Exception;

     void deleteUserById(Long userId) throws Exception;

     User depositCash(String username,BigDecimal amount);


}
