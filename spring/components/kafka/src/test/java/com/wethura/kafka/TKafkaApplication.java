package com.wethura.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.BatchMessageConverter;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;

@SpringBootApplication
public class TKafkaApplication {
    public static final String KAFKA_TEST_IMAGE = "bitnami/kafka:latest";

    static {
        KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse(KAFKA_TEST_IMAGE))
                .withEmbeddedZookeeper();
        kafkaContainer.setPortBindings(Arrays.asList("2181:2181", "9092:9092"));
        kafkaContainer.start();
    }

    @Bean
    RecordMessageConverter recordMessageConverter() {
        return new JsonMessageConverter();
    }

    @Bean
    BatchMessageConverter batchMessageConverter(RecordMessageConverter recordMessageConverter) {
        return new BatchMessagingMessageConverter(recordMessageConverter);
    }

    @Bean(name = "apple")
    NewTopic apple() {
        return TopicBuilder.name("apple").partitions(1).build();
    }

    @Bean(name = "banana")
    NewTopic banana() {
        return TopicBuilder.name("banana").partitions(1).build();
    }
}
