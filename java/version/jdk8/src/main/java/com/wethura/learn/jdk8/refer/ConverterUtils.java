package com.wethura.learn.jdk8.refer;

public class ConverterUtils implements Converter {
    @Override
    public int convert(String numberStr) {
        return Integer.decode(numberStr);
    }

    public static void echoNumber(String src) {
        System.out.println("Number: " + src);
    }
}
