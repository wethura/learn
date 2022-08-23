package com.wethura.multithread.juc;

import com.wethura.multithread.juc.futuretask.Counter;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureTaskTests {

    @Test
    void testMultiThreadSpeed() throws ExecutionException, InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        long result = 0;

        ExecutorService exec = Executors.newFixedThreadPool(20);
        ArrayList<FutureTask> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            FutureTask task = new FutureTask(new Counter(i * 1000000, (i + 1) * 1000000));
            tasks.add(task);
            exec.submit(task);
        }

        result = 0;
        for (FutureTask<Long> task : tasks) {
            result += task.get();
        }

        watch.stop();
        System.out.println("consuming times: " + watch.getNanoTime() + " result: " + result);

        watch.reset();
        watch.start();

        for (int i = 0; i < 10000000; i++) {
            result += i;
        }

        watch.stop();
        System.out.println("consuming times: " + watch.getNanoTime() + " result: " + result);

    }

}
