package com.example.videbank.service;

import com.example.videbank.dto.LoginRequestDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    UserDetails authenticateUser(LoginRequestDto loginRequest);

    Authentication authenticate(LoginRequestDto loginRequest) throws AuthenticationException;

    boolean authenticate(String username, String password);

    String generateToken(String username);
}

