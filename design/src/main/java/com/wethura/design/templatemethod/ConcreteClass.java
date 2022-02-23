package com.wethura.design.templatemethod;

public class ConcreteClass extends AbstractClass {
    @Override
    protected void abstractMethod() {
        System.out.println("You are execute concrete class's method.");
    }
}
