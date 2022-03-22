package com.wethura.design.servicelocator;

import org.junit.Assert;
import org.junit.Test;

public class ServiceLocatorTest extends Assert {

    @Test
    public void testServiceLocator() {
        Service apple_1 = ServiceLocator.getService("Apple");
        Service apple_2 = ServiceLocator.getService("Apple");

        apple_1.execute();
        apple_2.execute();

        assertEquals(apple_1, apple_2);

        Service banana_1 = ServiceLocator.getService("Banana");
        Service banana_2 = ServiceLocator.getService("Banana");

        banana_1.execute();
        banana_2.execute();

        assertEquals(banana_1, banana_2);
    }

}
