package com.jigowatts.springboot_with_mybatis.auth;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class MyAuthenticationUserDetailService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {

        Object apiKey = token.getCredentials();

        if ("1234-5678-90ab".equals(apiKey.toString())) {
            return User.withUsername("user").password("").authorities("ROLE_USER").build();
        }
        
        throw new UsernameNotFoundException("API Key not found.");
    }

}