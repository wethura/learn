package com.wethura.kafka.consumer;

import com.wethura.kafka.KafkaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class EchoConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoConsumer.class);

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.DEFAULT_TOPICS,
            partitions = {"0", "1"}), groupId = "default",properties = "isolation.level:read_uncommitted")
    public void listen00_01(String key, String data) {
        LOG.info("consumer-0/1: \nkey: {}\nvalue: {}", key, data);
    }


    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.DEFAULT_TOPICS,
            partitions = {"2"}), groupId = "default", properties = "isolation.level:read_uncommitted")
    public void listen02(String key, String data) {
        LOG.info("consumer-2: \nkey: {}\n:value: {}", key, data);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.DEFAULT_TOPICS,
    partitions = {"0", "1", "2"}), groupId = "jack", properties = "isolation.level:read_uncommitted")
    public void listen_00_01_02__jack(String key, String data) {
        LOG.info("consumer-0/1/2-jack: \nkey: {}\n:value: {}", key, data);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.DEFAULT_TOPICS,
    partitions = {"0", "1", "2"}), groupId = "william", properties = "isolation.level:read_uncommitted")
    public void listen_00_01_02__william(String key, String data) {
        LOG.info("consumer-0/1/2-william: \nkey: {}\n:value: {}", key, data);
    }

}
