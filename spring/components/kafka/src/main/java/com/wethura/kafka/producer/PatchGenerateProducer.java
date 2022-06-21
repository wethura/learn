package com.wethura.kafka.producer;

import com.wethura.kafka.KafkaConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Order(1)
public class PatchGenerateProducer implements InitializingBean {
    @Autowired
    KafkaTemplate<Object, Object> template;

    @Override
    public void afterPropertiesSet() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(this::buildMessage, 10, TimeUnit.SECONDS);
    }

    public void buildMessage() {
        int cnt = 0;
        while(true) {
            cnt ++;
            try {
                /*
                 * sleep for a moment.
                 */
                TimeUnit.MILLISECONDS.sleep(1);

                sendMsg(cnt);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    void sendMsg(int cnt) {
        template.setAllowNonTransactional(true);
        template.send(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_01,
                randomKey(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_01, cnt),
                data(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_01, cnt));
        template.send(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_02,
                randomKey(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_02, cnt),
                data(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_02, cnt));
        template.send(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_00,
                randomKey(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_00, cnt),
                data(KafkaConstants.DEFAULT_TOPICS, KafkaConstants.CONSUMER_PARTITION_00, cnt));
    }
    private String randomKey(String name, int partition, int cnt) {
        return String.format("%s-%s_%d: %s", name, partition, cnt, new Random().nextInt());
    }

    private String data(String name, int partition, int cnt) {
        return String.format("topic: %s; partition: %s; cnt: %s", name, partition, cnt);
    }
}