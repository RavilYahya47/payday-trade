package com.ravilyahya.paydaytrade.service;

import com.ravilyahya.paydaytrade.model.User;

import javax.mail.MessagingException;

public interface EmailSenderService {
     void sendActivationEmail(User user) throws MessagingException;

      void sendHtmlMail( ) throws MessagingException;
}
