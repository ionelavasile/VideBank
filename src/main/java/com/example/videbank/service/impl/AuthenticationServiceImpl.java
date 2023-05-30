package com.example.videbank.service.impl;

import com.example.videbank.dto.LoginRequestDto;
import com.example.videbank.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDetails authenticateUser(LoginRequestDto loginRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        Authentication authenticated = authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return userDetailsService.loadUserByUsername(loginRequest.getUsername());
    }

    @Override
    public Authentication authenticate(LoginRequestDto loginRequest) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        return authenticationManager.authenticate(authentication);
    }

    @Override
    public boolean authenticate(String username, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username,
                password
        );

        try {
            Authentication authenticated = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    public String generateToken(String username) {
        return null;
    }
}
