package com.example.springbootlearning.service;

import com.example.springbootlearning.Database.Entity.RefreshToken;
//import org.springframework.transaction.annotation.Transactional;

//import jakarta.transaction.Transactional;

public interface RefreshTokenService {
    RefreshToken save(RefreshToken r);
    RefreshToken findByRefreshToken(String token);

}