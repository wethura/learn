package com.wethura.multithread.awaitandsignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * use Condition.await() Condition.signal() for control the order of thread.
 */
public class OrderedPrinter {
    private Lock lock = new ReentrantLock();

    private Condition ac = lock.newCondition();
    private Condition bc = lock.newCondition();
    private Condition cc = lock.newCondition();

    private char signal = 'A';

    public void printA() {
        lock.lock();
        try {
            while(signal != 'A') {
                ac.await();
            }

            System.out.println("Echo A!");

            signal = 'B';
            bc.signal();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while(signal != 'B') {
                bc.await();
            }

            System.out.println("Echo B!");

            signal = 'C';
            cc.signal();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        lock.lock();
        try {
            while(signal != 'C') {
                cc.await();
            }

            System.out.println("Echo C!");

            signal = 'A';
            ac.signal();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

    }
}
