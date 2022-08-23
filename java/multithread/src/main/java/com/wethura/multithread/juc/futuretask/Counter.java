package com.wethura.multithread.juc.futuretask;

import java.util.concurrent.Callable;

public class Counter implements Callable<Long> {

    private final int from;
    private final int to;

    public Counter(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        long result = 0L;
        for (int i = from; i < to; i ++) {
            result += i;
        }
        return result;
    }
}
