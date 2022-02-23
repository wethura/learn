package com.wethura.design.templatemethod;

public abstract class AbstractClass {
    public void templateExecute() {
        concreteMethod();
        abstractMethod();
    }

    protected void concreteMethod() {
        System.out.println("You are execute abstract class's method.");
    }

    protected abstract void abstractMethod();

}
