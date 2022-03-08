package com.wethura.design.servicelocator;

public class AppleService implements Service{

    @Override
    public String getName() {
        return "Apple";
    }

    @Override
    public void execute() {
        System.out.println("Execute " + getName());
    }
}
