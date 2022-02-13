package com.wethura.spring.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wethura
 */
public class RedisTest {

    @Autowired
    private Redis redis;

    @Test
    public void test_1() {
        redis.redisCore();
    }
}
