package com.wethura.base.jvm;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Read Me before you run it.
 * <p>this class is to show the memory of jvm changing while the different reference be used.</p>
 * <p>1. storage reference: can not be release, the process will be stop while OutOfMemoryErr</p>
 * <p>2. soft reference: can not release memory util memory fulled, in other word: before OutOfMemoryErr</p>
 * <p>3. weak reference: release the memory anywhere when GC.</p>
 * <p/>
 * how to show the graphic.
 * <p>1. install JVisualVM</p>
 * <p>2. install the plugin of JVisualVm, which named Visual GC</p>
 * <p>3. open JVisualVM</p>
 * <p>4. run your process, select the process you run in VisualVM.</p>
 * <p>5. select the view VisualGC.</p>
 */
public class MemoryDynamicTests {

    private final Duration maxTimeToView = Duration.ofSeconds(2);

    Cache<Object, Object> storageCache = CacheBuilder.newBuilder().build();
    Cache<Object, Object> weakCache = CacheBuilder.newBuilder().weakKeys().weakValues().build();
    Cache<Object, Object> softCache = CacheBuilder.newBuilder().softValues().build();


    @Test
    @SuppressWarnings("all")
    public void testChangingMemoryToObserveStrongJvm() {
        LocalDateTime deadline = LocalDateTime.now().plus(maxTimeToView);
        while (true) {
            String key = RandomStringUtils.randomAlphabetic(10, 100);
            String value = RandomStringUtils.randomAlphabetic(1000, 10000);
            storageCache.put(key, value);
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testChangingMemoryToObserveWeakJvm() throws InterruptedException {
        LocalDateTime deadline = LocalDateTime.now().plus(maxTimeToView);
        while (true) {
            String key = RandomStringUtils.randomAlphabetic(10, 100);
            String value = RandomStringUtils.randomAlphabetic(1000, 10000);
            weakCache.put(key, value);

            if (LocalDateTime.now().isAfter(deadline)) {
                break;
            }
        }
    }

    @Test
    @SuppressWarnings("all")
    public void testChangingMemoryToObserveSoftJvm() throws InterruptedException {
        LocalDateTime deadline = LocalDateTime.now().plus(maxTimeToView);
        while (true) {
            String key = RandomStringUtils.randomAlphabetic(10, 100);
            String value = RandomStringUtils.randomAlphabetic(1000, 10000);
            softCache.put(key, value);

            if (LocalDateTime.now().isAfter(deadline)) {
                break;
            }
        }
    }
}
