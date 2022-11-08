package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.dao.request.ReqSingUpUser;
import com.ravilyahya.paydaytrade.dao.response.RespUser;
import com.ravilyahya.paydaytrade.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface UserService {
     RespUser createNormalUser(ReqSingUpUser user) throws Exception;

     RespUser getUserByUsername (String username) throws Exception;

     void deleteUserById(Long userId) throws Exception;

     RespUser depositCash(String username,BigDecimal amount);


}
