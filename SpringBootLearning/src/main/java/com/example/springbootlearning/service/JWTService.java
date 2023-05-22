package com.example.springbootlearning.service;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import com.example.springbootlearning.request.AuthRequest;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;

public interface JWTService {
    String generateToken(AuthRequest authRequest);
    String generateToken(String username);
    String generateToken(RefreshToken refreshToken);
    void validateToken(String token) throws AuthException;
    boolean valid(HttpServletRequest request);
    String parseJwt(HttpServletRequest request);
    Boolean isTokenExpired(String token);
    String getUsernameFromToken(String token);
}
