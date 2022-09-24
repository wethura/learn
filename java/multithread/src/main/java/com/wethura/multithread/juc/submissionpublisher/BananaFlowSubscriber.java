package com.wethura.multithread.juc.submissionpublisher;

import java.util.concurrent.Flow;

public class BananaFlowSubscriber implements Flow.Subscriber<String> {
    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }


    @Override
    public void onNext(String item) {
        System.out.printf("BananaFlowSubscriber: %s receive %s", Thread.currentThread().getName(), item);
    }
    @Override
    public void onError(Throwable throwable) {
        System.out.printf("BananaFlowSubscriber: something error, %s", throwable.getMessage());
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
