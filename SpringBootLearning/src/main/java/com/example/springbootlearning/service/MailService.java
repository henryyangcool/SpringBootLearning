package com.example.springbootlearning.service;

public interface MailService {
    void mailSend(String sendFrom, String sendTo, String subject, String text);
}
