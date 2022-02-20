package com.wethura.spring.redis;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author wethura
 * @date 2021/1/25 上午3:18
 */
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void set(String k, String... values) {
        redisTemplate.opsForSet().add(k, values);
    }

    public Set<String> members(String k) {
        return redisTemplate.opsForSet().members(k);
    }
}
