package com.wethura.multithread.juc.reentrantlock;

public class AppleStore implements Runnable {
    private TicketCoreStore realStore;

    public AppleStore(TicketCoreStore realStore) {
        this.realStore = realStore;
    }

    @Override
    public void run() {
        while(realStore.saleTicket()) System.out.println("Apple Store sold ticket success.");;
    }
}
