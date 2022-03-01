package com.wethura.design.factory;

import org.junit.Test;

public class FactoryPatternTest {

    @Test
    public void testGetCircleByFactory() {
        ShapeFactory.getShare(Circle.class).draw();
    }
    @Test
    public void testGetRectangleByFactory() {
        ShapeFactory.getShare(Rectangle.class).draw();
    }
    @Test
    public void testGetSquareByFactory() {
        ShapeFactory.getShare(Square.class).draw();
    }
}