package com.wethura.learn.jdk8.defaultinterface;

import org.junit.Test;

public class DefaultMethodInterfaceTest {
    @Test
    public void testDefaultMethodForInterface() {
        DefaultAppleMethodInterface methodInterface = new DefaultAppleMethodInterface();

        // default method for interface.
        methodInterface.defaultMethodForInterface();

        // implement the interface's method.
        System.out.println(methodInterface.methodForInterface());
    }
}
