package com.example.springbootlearning.service.Impl;

import com.example.springbootlearning.Database.Entity.RefreshToken;
import com.example.springbootlearning.Database.Repository.RefreshTokenRepository;
import com.example.springbootlearning.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RefreshTokenService")
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken save(RefreshToken r) {
        return refreshTokenRepository.save(r);
    }
    @Override
    public RefreshToken findByRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
