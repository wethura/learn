package com.wethura.multithread.juc;

import com.wethura.multithread.juc.syncqueue.Consumer;
import com.wethura.multithread.juc.syncqueue.Producer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SyncQueueTests {
    @Test
    void testSyncQueueConsumer() throws InterruptedException {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(new Producer(queue), "beijing product line").start();
        new Thread(new Producer(queue), "guangdong product line").start();

        new Thread(new Consumer(queue), "nanjing dealer").start();
        new Thread(new Consumer(queue), "foreign dealer").start();
        new Thread(new Consumer(queue), "jinan dealer").start();

        TimeUnit.SECONDS.sleep(1);
    }
}
