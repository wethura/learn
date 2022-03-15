package com.wethura.design.prototype;

import org.junit.Test;
import org.springframework.util.StopWatch;

public class PrototypeTest {
    public static final int NUMBER_NEW = 1 << 26;

    @Test
    public void testPrototype() {
        StopWatch watch = new StopWatch();
        watch.start();
        for (int i = 0; i < NUMBER_NEW; i++) {
            Shape ignored = ShareCache.getShape("rectangle");
        }
        watch.stop();
        System.out.println("prototype builder: " + watch.getTotalTimeMillis() + " ms");

        watch.start();
        for (int i = 0; i < NUMBER_NEW; i++) {
            Rectangle ignored = new Rectangle();
        }
        watch.stop();
        System.out.println("construct builder: " + watch.getTotalTimeMillis() + " ms");

//        根据运行的结果，不是很符合预期的效果。可能由于当前的对象比较简单没有那种比较复杂的效果体现的明显。
    }

}
