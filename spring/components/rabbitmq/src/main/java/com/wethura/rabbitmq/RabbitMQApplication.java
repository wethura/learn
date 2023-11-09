package com.wethura.rabbitmq;

import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_EXCHANGE_NAME;
import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_QUEUE_NAME;
import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_ROUTE_KEY;

import com.wethura.rabbitmq.consumer.EchoConsumer;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateConfigurer;
import org.springframework.context.annotation.Bean;

/**
 * @author sola
 **/
@SpringBootApplication
public class RabbitMQApplication {

    public static AtomicInteger confirmMsgCount = new AtomicInteger(0);

    public static void main(String[] args) {
        SpringApplication.run(RabbitMQApplication.class, args);
    }

    @Bean
    Queue queue() {
        return new Queue(DEFAULT_QUEUE_NAME, true, false, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(DEFAULT_EXCHANGE_NAME, true, false);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEFAULT_ROUTE_KEY);
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EchoConsumer consumer) {
        final MessageListenerAdapter adapter = new MessageListenerAdapter(consumer, "receiveMessage");
        return adapter;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(RabbitTemplateConfigurer configurer, ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        configurer.configure(template, connectionFactory);
        template.containerAckMode(AcknowledgeMode.MANUAL);
        template.setChannelTransacted(true);
        template.setConfirmCallback((correlationData, ack, cause) -> {
            confirmMsgCount.incrementAndGet();
        });
        return template;
    }
}
