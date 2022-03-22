package com.wethura.design.builder.drink;

public class Coco extends ColdDrink{

    @Override
    public String name() {
        return "Drink Coco";
    }

    @Override
    public float price() {
        return 3.f;
    }
}
