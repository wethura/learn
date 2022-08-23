package com.wethura.multithread.waitandnotify;

public class CounterAndPrinter {

    private int count = 0;
    private static final int MAX_STOCK = 3;

    public synchronized void produce() {
        while(count >= MAX_STOCK) {
            try {
                wait();
            } catch (InterruptedException ignored){
            }
        }

        System.out.println(Thread.currentThread().getName() + " succeed produce 1 product, current: " + (++count));
        notifyAll();
    }

    public synchronized void consume() {
        while(count <= 0) {
            try {
                wait();
            } catch (InterruptedException ignored){
            }
        }

        System.out.println(Thread.currentThread().getName() + " succeed sale 1 product, current: " + (--count));
        notifyAll();
    }
}
