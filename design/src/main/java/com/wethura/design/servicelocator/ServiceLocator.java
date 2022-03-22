package com.wethura.design.servicelocator;

import java.util.Objects;

public class ServiceLocator {

    private static Cache cache = new Cache();

    public static Service getService(String jndiName) {
        Service service = cache.getService(jndiName);

        if (Objects.nonNull(service)) {
            return service;
        }

        Service lookup = new InitialContext().lookup(jndiName);
        cache.add(lookup);

        return lookup;
    }

}
