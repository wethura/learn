package com.wethura.design.servicelocator;

public class BananaService implements Service{

    @Override
    public String getName() {
        return "Banana";
    }

    @Override
    public void execute() {
        System.out.println("Executing " + getName());
    }
}
