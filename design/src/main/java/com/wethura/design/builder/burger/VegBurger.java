package com.wethura.design.builder.burger;

public class VegBurger extends Burger{

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 25.f;
    }
}
