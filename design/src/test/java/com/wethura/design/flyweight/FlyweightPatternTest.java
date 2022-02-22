package com.wethura.design.flyweight;

import org.junit.Assert;
import org.junit.Test;

public class FlyweightPatternTest extends Assert {
    @Test
    public void testPattern() {
        final Flyweight flyweight_a_01 = FlyweightFactory.getFlyweight("flyweight a");
        final Flyweight flyweight_a_02 = FlyweightFactory.getFlyweight("flyweight a");
        final Flyweight flyweight_a_03 = FlyweightFactory.getFlyweight("flyweight a");

        final Flyweight flyweight_b_01 = FlyweightFactory.getFlyweight("flyweight b");
        final Flyweight flyweight_b_02 = FlyweightFactory.getFlyweight("flyweight b");

        System.out.println("-----------------------------sep------------------------------");

        flyweight_a_01.execute(new UnsharedConcreteFlyweight("1st time execute flyweight a"));
        flyweight_a_02.execute(new UnsharedConcreteFlyweight("2st time execute flyweight a"));
        flyweight_a_03.execute(new UnsharedConcreteFlyweight("3st time execute flyweight a"));

        flyweight_b_01.execute(new UnsharedConcreteFlyweight("1st time execute flyweight b"));
        flyweight_b_02.execute(new UnsharedConcreteFlyweight("2st time execute flyweight b"));

        // assert equal a_$x
        assertEquals(flyweight_a_01, flyweight_a_02);
        assertEquals(flyweight_a_01, flyweight_a_03);

        // assert equal b_$x
        assertEquals(flyweight_b_01, flyweight_b_02);

        // assert not equal a, b
        assertNotEquals(flyweight_a_01, flyweight_b_01);
    }
}
