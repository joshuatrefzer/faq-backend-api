package com.example.faq_backend_api.config;

import com.example.faq_backend_api.api.controller.FAQController;
import com.example.faq_backend_api.api.interfaces.security.JwtAuthenticationEntrypoint;
import com.example.faq_backend_api.api.interfaces.security.JwtAuthenticationFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    private JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;

   

    public JwtAuthenticationFilter jwtAuthenticationFilter() {}

    @Override
    public void configure(HttpSecurity http) throws Exeption  {


    }
    
}
