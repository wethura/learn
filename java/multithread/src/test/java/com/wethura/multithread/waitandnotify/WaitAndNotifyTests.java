package com.wethura.multithread.waitandnotify;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class WaitAndNotifyTests {
    @Test
    public void testProduceAndConsume() throws InterruptedException {
        CounterAndPrinter counterAndPrinter = new CounterAndPrinter();

        new Thread(() -> {
            while(true) {
                counterAndPrinter.produce();
            }
        }, "assembly line 01").start();

        new Thread(() -> {
            while(true) {
                counterAndPrinter.produce();
            }
        }, "assembly line 02").start();

        new Thread(() -> {
            while(true) {
                counterAndPrinter.consume();
            }
        }, "taobao").start();

        new Thread(() -> {
            while(true) {
                counterAndPrinter.consume();
            }
        }, "jd").start();

        TimeUnit.SECONDS.sleep(10);
    }
}
