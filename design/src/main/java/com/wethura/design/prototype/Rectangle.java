package com.wethura.design.prototype;

public class Rectangle extends Shape{

    public Rectangle() {
        super("Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Draw rectangle.");
    }
}
