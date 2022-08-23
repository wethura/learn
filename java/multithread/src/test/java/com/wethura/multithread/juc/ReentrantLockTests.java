package com.wethura.multithread.juc;

import com.wethura.multithread.juc.reentrantlock.AppleStore;
import com.wethura.multithread.juc.reentrantlock.BananaStore;
import com.wethura.multithread.juc.reentrantlock.TicketCoreStore;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ReentrantLockTests {
    @Test
    public void testSoldTick() throws InterruptedException {
        TicketCoreStore coreStore = new TicketCoreStore();
        AppleStore appleStore = new AppleStore(coreStore);
        BananaStore bananaStore = new BananaStore(coreStore);

        new Thread(appleStore).start();
        new Thread(bananaStore).start();

        TimeUnit.SECONDS.sleep(10);
    }
}
