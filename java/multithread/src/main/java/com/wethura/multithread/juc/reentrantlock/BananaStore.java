package com.wethura.multithread.juc.reentrantlock;

public class BananaStore implements Runnable{

    private TicketCoreStore realStore;

    public BananaStore(TicketCoreStore realStore) {
        this.realStore = realStore;
    }

    @Override
    public void run() {
        while(realStore.saleTicket()) System.out.println("Banana Store sold ticket success.");;
    }
}
