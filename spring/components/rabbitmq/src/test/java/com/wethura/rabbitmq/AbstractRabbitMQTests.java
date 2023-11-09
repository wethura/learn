package com.wethura.rabbitmq;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author sola
 **/
@SpringBootTest(classes = RabbitMQApplication.class)
abstract public class AbstractRabbitMQTests extends Assertions {

}
