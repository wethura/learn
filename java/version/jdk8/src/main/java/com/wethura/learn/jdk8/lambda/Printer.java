package com.wethura.learn.jdk8.lambda;

public class Printer {
    public void echo(StringProvider api) {
        System.out.println(api.get("Printer->Echo()"));
    }
}
