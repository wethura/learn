package com.wethura.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.converter.BatchMessageConverter;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.Properties;

import static com.wethura.kafka.KafkaConstants.CONSUMER_TOTAL_PARTITION;

@EnableTransactionManagement
@SpringBootApplication
public class TKafkaApplication {
    public static final String KAFKA_TEST_IMAGE = "bitnami/kafka:latest";
    public static final String ZOOKEEPER_TEST_IMAGE = "bitnami/zookeeper:latest";
    public static GenericContainer kafkaContainer;
    public static GenericContainer zookeeperContainer;

    static {
        zookeeperContainer = new GenericContainer(DockerImageName.parse(ZOOKEEPER_TEST_IMAGE));
        zookeeperContainer.setPortBindings(Arrays.asList("2181:2181"));
        zookeeperContainer.addEnv("ZOOKEEPER_CLIENT_PORT", "2181");
        zookeeperContainer.addEnv("ZOOKEEPER_SECURE_CLIENT_PORT", "2182");
        zookeeperContainer.addEnv("ALLOW_ANONYMOUS_LOGIN", "yes");
        zookeeperContainer.start();

        kafkaContainer = new GenericContainer(DockerImageName.parse(KAFKA_TEST_IMAGE));
        kafkaContainer.setPortBindings(Arrays.asList("9092:9092", "9093:9093"));
        kafkaContainer.addEnv("KAFKA_ADVERTISED_LISTENERS", "PLAINTEXT://192.168.8.122:9092");
        kafkaContainer.addEnv("KAFKA_BROKER_ID", "1");
        kafkaContainer.addEnv("KAFKA_ZOOKEEPER_CONNECT", "192.168.8.122:2181");
        kafkaContainer.addEnv("ALLOW_PLAINTEXT_LISTENER", "yes");
        kafkaContainer.addEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "PLAINTEXT:PLAINTEXT,BROKER:PLAINTEXT");
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

    @Bean(name = KafkaConstants.DEFAULT_TOPICS)
    NewTopic defaultTopics() {
        return TopicBuilder.name(KafkaConstants.DEFAULT_TOPICS).partitions(CONSUMER_TOTAL_PARTITION).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(TKafkaApplication.class, args);
    }
}
