package com.wethura.learn.jdk8.defaultinterface;

public class DefaultAppleMethodInterface implements DefaultMethodInterface {

    @Override
    public String methodForInterface() {
        return "You get string from interface's implement.";
    }
}
