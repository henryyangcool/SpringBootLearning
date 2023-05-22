package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.service.MailService;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service("MailService")
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;
    public void mailSend(String sendFrom, String sendTo, String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sendFrom);
        message.setTo(sendTo);
        message.setSubject(subject);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowTime = now.format(formatter);
        String mess = "Time: " + nowTime;
        message.setText(mess + "\n" + text);

        javaMailSender.send(message);
    }
}

