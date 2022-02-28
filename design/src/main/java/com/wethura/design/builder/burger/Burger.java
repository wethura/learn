package com.wethura.design.builder.burger;

import com.wethura.design.builder.common.Item;
import com.wethura.design.builder.common.Packing;
import com.wethura.design.builder.common.Wrapper;

public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
