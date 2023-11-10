package com.example.etc_service.config.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "plaintext://172.18.0.101:9092"); // kafka container host
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "etc-service"); // consumer group id 그룹으로 지정해서 보내기 가능
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); // key deserializer
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class); // value deserializer
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("test")
                .partitions(10)
                .replicas(1)
                .build();
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        // kafka listener container factory
        ConcurrentKafkaListenerContainerFactory<String, String>
                kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory()); // consumer factory
        // kafkaListenerContainerFactory.setBatchListener(true); // batch listener
        // kafkalistnerContainerFactory.setConcurrency(3); // concurrency
        return kafkaListenerContainerFactory;
    }
}
