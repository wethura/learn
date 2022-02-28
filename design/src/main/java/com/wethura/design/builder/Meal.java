package com.wethura.design.builder;

import com.wethura.design.builder.burger.ChickenBurger;
import com.wethura.design.builder.burger.VegBurger;
import com.wethura.design.builder.common.Item;
import com.wethura.design.builder.drink.Coco;
import com.wethura.design.builder.drink.Pepsi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Meal {
    private List<Item> items = new ArrayList<>();

    protected void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        AtomicReference<Double> cost = new AtomicReference<>(0.d);
        items.forEach(i -> cost.updateAndGet(old -> old + i.price()));

        return (float) (1.0f * cost.get());
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println(String.format("Item: %s, Packing: %s, Price: %s",
                    item.name(), item.packing().packing(), item.price()));
        }
    }

    public static class MealBuilder {
        public Meal prepareVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new VegBurger());
            meal.addItem(new Coco());

            return meal;
        }

        public Meal prepareNonVegMeal() {
            Meal meal = new Meal();
            meal.addItem(new ChickenBurger());
            meal.addItem(new Pepsi());

            return meal;
        }
    }
}
