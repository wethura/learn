package com.wethura.learn.jdk8.lambda;

import org.junit.Test;

public class LambdaTest {
    @Test
    public void testLambda() {
        new Printer().echo(src -> "You getting " + src);
    }
}
