package com.wethura.multithread.juc.syncqueue;

import java.util.UUID;
import java.util.concurrent.SynchronousQueue;

public class Producer implements Runnable{
    private final SynchronousQueue<String> queue;

    public Producer(SynchronousQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true) {
            String uuid = UUID.randomUUID().toString();
            try {
                queue.put(uuid);
                System.out.printf("manufacturer: %s produce product no.: %s%n", Thread.currentThread().getName(), uuid);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
