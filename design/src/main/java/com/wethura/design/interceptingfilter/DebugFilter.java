package com.wethura.design.interceptingfilter;

public class DebugFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("Debug: " + request);
    }
}
