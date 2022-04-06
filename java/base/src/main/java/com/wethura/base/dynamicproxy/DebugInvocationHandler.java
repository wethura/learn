package com.wethura.base.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebugInvocationHandler implements InvocationHandler {

    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            System.out.println("handle before work...");
            Object result = method.invoke(target, args);
            System.out.println("handle after work...");
            return result;
        } catch (InvocationTargetException e) {
            System.out.printf("handle after throwing... error msg: %s%n", e.getTargetException().getMessage());
            throw e.getTargetException();
        }
    }
}
