package com.jigowatts.springboot_with_mybatis.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

public class ApiKeyAuthManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) {
        String principal = (String) authentication.getPrincipal();
        if (principal != null && principal.equals("1234-5678-90ab")) {
            authentication.setAuthenticated(true);
            return authentication;
        } else {
            throw new BadCredentialsException("API key is invalid.");
        }
    }

}