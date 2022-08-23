package com.wethura.multithread.awaitandsignal;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class AwaitAndSignalTests {
    @Test
    public void testOrderedPrinter() throws InterruptedException {
        OrderedPrinter printer = new OrderedPrinter();

        new Thread(() -> {
            while (true) {
                printer.printA();
            }
        }, "A printer").start();

        new Thread(() -> {
            while (true) {
                printer.printB();
            }
        }, "B printer").start();

        new Thread(() -> {
            while (true) {
                printer.printC();
            }
        }, "C printer").start();

        TimeUnit.SECONDS.sleep(1);
    }
}
