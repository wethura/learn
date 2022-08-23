package com.wethura.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class WebSecurityConfiguration {
    @Bean(name = "SecurityFilterChain_01")
    SecurityFilterChain securityFilterChain_01(HttpSecurity http) throws Exception {
        return http.antMatcher("/hello/permit").authorizeRequests().anyRequest().permitAll().and().build();
    }

    @Bean(name = "SecurityFilterChain_02")
    SecurityFilterChain securityFilterChain_02(HttpSecurity http) throws Exception {
        return http.antMatcher("/hello/no_permit").authorizeRequests().anyRequest().authenticated().and().build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> new User(username, "{noop}password", Arrays.asList(new SimpleGrantedAuthority("admin")));
    }
}