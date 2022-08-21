package com.wethura.spring.controller;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableWebSecurity
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/permit")
    public String permit() {
        return "Hello World!";
    }

    @GetMapping("/no_permit")
    public String no_permit() {
        return "Hello World!";
    }
}
