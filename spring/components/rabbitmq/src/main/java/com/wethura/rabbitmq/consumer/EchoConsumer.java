package com.wethura.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sola
 **/
@Component
@RabbitListener
public class EchoConsumer {

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
//        final int i = 1 / 0;
    }
}
