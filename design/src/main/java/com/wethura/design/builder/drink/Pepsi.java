package com.wethura.design.builder.drink;

public class Pepsi extends ColdDrink{

    @Override
    public String name() {
        return "Drink Pepsi";
    }

    @Override
    public float price() {
        return 3.5f;
    }
}
