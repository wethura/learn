package com.wethura.base.dynamicproxy;

import java.lang.reflect.Proxy;

public class JdkProxyFactory {
    public static Object getProxyObject(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new DebugInvocationHandler(obj));
    }
}
