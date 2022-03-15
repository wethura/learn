package com.wethura.design.prototype;

public class Square extends Shape {

    public Square() {
        super("Square");
    }

    @Override
    public void draw() {
        System.out.println("Draw square.");
    }
}
