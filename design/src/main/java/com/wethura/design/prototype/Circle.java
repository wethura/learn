package com.wethura.design.prototype;

public class Circle extends Shape{

    public Circle() {
        super("Circle");
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle.");
    }
}
