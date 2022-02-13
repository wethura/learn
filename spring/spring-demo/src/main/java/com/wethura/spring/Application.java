package com.wethura.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * @author wethura
 * @date 2021/1/25 上午3:55
 */
@EnableRedisRepositories
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final SpringApplication application = new SpringApplication(Application.class);
        application.setWebApplicationType(WebApplicationType.NONE);

        application.run();
    }
}
