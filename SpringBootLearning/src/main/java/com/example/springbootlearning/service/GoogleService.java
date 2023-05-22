package com.example.springbootlearning.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

//https://matthung0807.blogspot.com/2021/02/spring-boot-google-authenticator-totp-auth.html
public interface GoogleService {
    String createGoogleAuthenticatorKeyUrl(String secret);
    String generateCaptcha();
    String createGoogleAuthQRCodeData(String secret, String account);
    void getCaptcha(HttpServletRequest request, HttpServletResponse response, String captcha) throws Exception;
}
