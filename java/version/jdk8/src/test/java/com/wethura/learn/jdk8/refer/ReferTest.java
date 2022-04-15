package com.wethura.learn.jdk8.refer;

import java.util.function.Consumer;
import org.junit.Test;

public class ReferTest {
    @Test
    public void testRefer() {
        Converter converter = Integer::decode;

        System.out.println(converter.convert("12345"));

        Consumer<String> consumer = ConverterUtils::echoNumber;

        consumer.accept("99808");
    }
}
