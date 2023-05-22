package com.example.springbootlearning.service;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CookieService{
    void addCookie(HttpServletResponse response, String name, String value, long id, String username);
    void refreshCookie(HttpServletRequest request, HttpServletResponse response,
                       String name, String value, String username, String oldToken);
    RefreshToken getCookieData(HttpServletRequest request);
}
