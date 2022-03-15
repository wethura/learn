package com.wethura.design.prototype;

import java.util.Hashtable;

public class ShareCache {

    private static Hashtable<String, Shape> shapes = new Hashtable<>(3);

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapes.get(shapeId);

        return (Shape) cachedShape.clone();
    }

    // auto load cache.
    static {
        Circle circle = new Circle();
        circle.setId("circle");
        shapes.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("square");
        shapes.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("rectangle");
        shapes.put(rectangle.getId(), rectangle);
    }

}
