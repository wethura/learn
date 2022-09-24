package com.wethura.multithread.juc.submissionpublisher;

import java.util.function.Consumer;

public class WatermelonConsumer implements Consumer<String> {
    @Override
    public void accept(String str) {
        System.out.printf("WatermelonConsumer: %s receive %s", Thread.currentThread().getName(), str);
    }
}
