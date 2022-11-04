package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.model.Role;
import com.ravilyahya.paydaytrade.model.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User  getUserByUsername (String username) throws Exception;

    public void deleteUserById(Long userId) throws Exception;

}
