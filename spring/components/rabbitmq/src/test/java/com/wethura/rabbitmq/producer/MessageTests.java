package com.wethura.rabbitmq.producer;

import static com.wethura.rabbitmq.RabbitConstants.DEFAULT_QUEUE_NAME;

import com.wethura.rabbitmq.AbstractRabbitMQTests;
import com.wethura.rabbitmq.RabbitMQApplication;
import com.wethura.rabbitmq.vo.RabbitmqEchoVo;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

/**
 * @author sola
 **/
class MessageTests extends AbstractRabbitMQTests {

    @Autowired
    private PatchGenerateProducer producer;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private MessageListenerAdapter listenerAdapter;


    @Test
    void testSendRabbitMQMsg() {
        final StopWatch watch = new StopWatch();
        watch.start();
        int count = (int) (1e4), i = 0;
        for (i = 0; i < count; i++) {
            try {
                producer.sendMsg(new RabbitmqEchoVo("florida", "GUCCI"));
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        watch.stop();
        System.out.printf("\n\ntime: %d ms, produce msg: %d, confirm %d\nstatistics: %d msg/second\n\n",
                watch.getTotalTimeMillis(), i, RabbitMQApplication.confirmMsgCount.get(),
                i * 1000L / watch.getTotalTimeMillis());
    }

    @Test
    void testReceiveRabbitMQMsg() throws InterruptedException {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(DEFAULT_QUEUE_NAME);
        container.setMessageListener(listenerAdapter);

        container.start();
        TimeUnit.MINUTES.sleep(5);
    }
}
