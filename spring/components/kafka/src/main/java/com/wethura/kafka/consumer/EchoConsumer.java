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
            partitions = {"1", "2"}), properties = "isolation.level:read_uncommitted")
    public void listen01_02(String key, String data) {
        LOG.info("consumer-1/2: {} - {}", key, data);
    }


    @KafkaListener(topicPartitions = @TopicPartition(topic = KafkaConstants.DEFAULT_TOPICS,
            partitions = {"0"}), properties = "isolation.level:read_uncommitted")
    public void listen03(String key, String data) {
        LOG.info("consumer-3: {} - {}", key, data);
    }
}
