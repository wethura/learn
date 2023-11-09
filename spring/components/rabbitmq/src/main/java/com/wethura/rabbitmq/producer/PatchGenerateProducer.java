package com.wethura.rabbitmq.producer;

import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_EXCHANGE_NAME;
import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_ROUTE_KEY;

import com.wethura.rabbitmq.vo.RabbitmqEchoVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author sola
 **/
@Component
public class PatchGenerateProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(RabbitmqEchoVo msg) {
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE_NAME, DEFAULT_ROUTE_KEY, msg.toString());
    }
}
