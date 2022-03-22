package com.wethura.design.factory;

public class ShapeFactory {
    public static Shape getShare(Class<? extends Shape> clazz) {
        if (clazz.isAssignableFrom(Circle.class)) {
            return new Circle();
        } else if (clazz.isAssignableFrom(Rectangle.class)) {
            return new Rectangle();
        } else if(clazz.isAssignableFrom(Square.class)) {
            return new Rectangle();
        }

        throw new IllegalArgumentException("the specify shape " + clazz.getName() + " not support!");
    }
}
