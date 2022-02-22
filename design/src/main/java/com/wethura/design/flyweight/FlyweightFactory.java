package com.wethura.design.flyweight;

import java.util.HashMap;

public class FlyweightFactory {
    private  static HashMap<String, Flyweight> flyweights = new HashMap<>();

    public static Flyweight getFlyweight(String keyword) {
        Flyweight flyweight = flyweights.get(keyword);
        if (flyweight != null) {
            System.out.println("You getting the exist FLYWEIGHT! ");
        } else {
            flyweight = new ConcreteFlyweight(keyword);
            flyweights.put(keyword, flyweight);
        }
        return flyweight;
    }

}
