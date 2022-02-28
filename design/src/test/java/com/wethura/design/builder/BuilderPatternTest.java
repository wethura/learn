package com.wethura.design.builder;

import com.wethura.design.builder.Meal.MealBuilder;
import org.junit.Test;

public class BuilderPatternTest {

    @Test
    public void testBuilderForNonVegMeal() {
        Meal meal = new MealBuilder().prepareNonVegMeal();
        System.out.println("套餐: ");
        meal.showItems();
        System.out.println(String.format("价格: %s", meal.getCost()));
    }

    @Test
    public void testBuilderVegMeal() {
        Meal meal = new MealBuilder().prepareVegMeal();
        System.out.println("套餐: ");
        meal.showItems();
        System.out.println(String.format("价格: %s", meal.getCost()));
    }
}
