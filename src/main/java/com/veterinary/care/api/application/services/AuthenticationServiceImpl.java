package com.veterinary.care.api.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.veterinary.care.api.application.interfaces.AuthenticationService;
import com.veterinary.care.api.application.interfaces.JwtService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtService jwtService;

    public String authenticate(Authentication authentication) {
        return jwtService.generateToken(authentication);
    }
}
