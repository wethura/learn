package com.wethura.learn.jdk8.defaultinterface;

public interface DefaultMethodInterface {

    String methodForInterface();

    /**
     * new feature.
     */
    default void defaultMethodForInterface() {
        System.out.println("You running a method in interface.");
    }
}
