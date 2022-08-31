package com.wethura.multithread.juc.submissionpublisher;

import java.util.function.Consumer;

public class SubmissionConsumer implements Consumer<String> {
    @Override
    public void accept(String str) {
        System.out.printf("consumer: %s receive %s", Thread.currentThread().getName(), str);
    }
}
