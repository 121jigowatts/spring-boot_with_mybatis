package com.jigowatts.springboot_with_mybatis.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(preAuthenticatedProcessingFilter()).authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        return new ApiKeyAuthManager();
    }

    @Bean
    public AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() {
        AbstractPreAuthenticatedProcessingFilter apiKeyFilter = new ApiKeyAuthFilter();
        apiKeyFilter.setAuthenticationManager(authenticationManager());
        return apiKeyFilter;
    }

}