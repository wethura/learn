package com.wethura.design.servicelocator;

import javax.management.ServiceNotFoundException;

public class InitialContext {
    public Service lookup(String jndiName) {
        if ("Apple".equalsIgnoreCase(jndiName)) {
            System.out.println("Looking up and create a new Apple Service object!");
            return new AppleService();
        } else if("Banana".equalsIgnoreCase(jndiName)) {
            System.out.println("Looking up and create a new Banana Service object!");
            return new BananaService();
        }

        throw new RuntimeException("Specify class not found.");
    }
}
