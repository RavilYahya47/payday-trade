package com.ravilyahya.paydaytrade.service.impl;

import com.ravilyahya.paydaytrade.model.User;
import com.ravilyahya.paydaytrade.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;

    @Value("${emailSubject}")
    String emailSubject;

    @Value("${baseUrl}")
    String baseUrl;


    public void sendActivationEmail(User user) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");


        String emailBody =String.format("""
                <h3>Hello %s</h3>
                Please click the link below to activate your account.
                <a href="%s/api/user/verify/%s/%s">Verify Account</a>
                """,user.getUsername(),baseUrl,user.getUsername(),user.getActivationCode());
        helper.setText(emailBody, true);
        helper.setTo(user.getEmail());
        helper.setSubject(emailSubject);
        javaMailSender.send(mimeMessage);


        System.out.println("Mail sent successfully");

    }


    public void sendHtmlMail( ) throws MessagingException {



    }
}
