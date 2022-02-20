package com.wethura.spring.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author wethura
 * @date 2021/1/25 上午3:22
 */
public class RedisAutoConfiguration {

    private static String  REDIS_HOST = "127.0.0.1";
    private static Integer REDIS_PORT = 6379;

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);

        // configuration.setDatabase();
        // configuration.setPassword();

        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisService redis() {
        return new RedisService();
    }

}
