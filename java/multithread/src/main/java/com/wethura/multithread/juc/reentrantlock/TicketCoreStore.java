package com.wethura.multithread.juc.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketCoreStore {
    Lock lock = new ReentrantLock();

    int totalTicket = 100;

    public boolean saleTicket() {
        lock.lock();
        try {
            if (totalTicket > 0 ) {
                totalTicket --;
                System.out.println("leave " + totalTicket + " tickets");
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }
}
