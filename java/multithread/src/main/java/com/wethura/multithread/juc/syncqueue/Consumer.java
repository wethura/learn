package com.wethura.multithread.juc.syncqueue;

import java.util.concurrent.SynchronousQueue;

public class Consumer implements Runnable{

    private final SynchronousQueue<String> queue;

    public Consumer(SynchronousQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String uuid = queue.take();
                System.out.printf("dealer: %s consume product no.: %s%n", Thread.currentThread().getName(), uuid);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
