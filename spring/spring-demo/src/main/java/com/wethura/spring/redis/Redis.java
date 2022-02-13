package com.wethura.spring.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

/**
 * @author wethura
 * @date 2021/1/25 上午3:18
 */
public class Redis {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void redisCore() {
        final SetOperations<String, String> ops = redisTemplate.opsForSet();

        ops.add("winner", "wethura", "sola");
    }
}
