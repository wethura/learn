package com.wethura.design.builder.drink;

import com.wethura.design.builder.common.Bottle;
import com.wethura.design.builder.common.Item;
import com.wethura.design.builder.common.Packing;

public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
