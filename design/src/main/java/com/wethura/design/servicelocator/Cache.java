package com.wethura.design.servicelocator;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private List<Service> services = new ArrayList<>();

    public Service getService(String name) {
        for (Service service : services) {
            if (service.getName().equalsIgnoreCase(name)) {
                return service;
            }
        }

        return null;
    }

    public void add(Service service) {
        if (services.stream().noneMatch(s -> service.getName().equalsIgnoreCase(s.getName()))) {
            services.add(service);
        }
    }
}
