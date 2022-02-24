package com.wethura.design.interceptingfilter;

public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Authentication the request: " + request);
    }
}
