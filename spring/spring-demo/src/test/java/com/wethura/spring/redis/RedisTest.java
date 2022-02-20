package com.wethura.spring.redis;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author wethura
 */
@SpringBootTest
public class RedisTest extends Assert {

    public static final String REDIS_SET_TEST_KEY = "redis_set_test_key";
    public static final String REDIS_SET_TEST_VALUE_1 = "redis_set_test_value_1";
    public static final String REDIS_SET_TEST_VALUE_2 = "redis_set_test_value_2";
    @Autowired
    private RedisService redisService;

    @Test
    public void testAddAndGetForRedisSet() {
        redisService.set(REDIS_SET_TEST_KEY, REDIS_SET_TEST_VALUE_1, REDIS_SET_TEST_VALUE_2);
        final Set<String> members = redisService.members(REDIS_SET_TEST_KEY);

        Assert.isTrue(members.contains(REDIS_SET_TEST_VALUE_1), "get wrong value set after set");
        Assert.isTrue(members.contains(REDIS_SET_TEST_VALUE_2), "get wrong value set after set");
    }
}
