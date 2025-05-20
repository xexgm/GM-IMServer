package com.gm.server.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xexgm
 */
@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, String> defaultConsumerFactory(
            org.springframework.boot.autoconfigure.kafka.KafkaProperties properties) {
        Map<String, Object> configs = properties.buildConsumerProperties();
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "default-consumer-group");
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    // 用户状态专用消费者配置（对应 spring.kafka.user-status.consumer.*）
    @Bean
    public ConsumerFactory<String, String> userStatusConsumerFactory(
            org.springframework.boot.autoconfigure.kafka.KafkaProperties properties) {
        // 从主配置继承公共属性（如 bootstrap-servers）
        Map<String, Object> configs = new HashMap<>(properties.buildConsumerProperties());
        // 覆盖特定属性（优先级：user-status > 默认配置）
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "user-status-group");
        return new DefaultKafkaConsumerFactory<>(configs);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> defaultFactory(
            ConsumerFactory<String, String> defaultConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(defaultConsumerFactory);
        return factory;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> statusFactory(
            @Qualifier("userStatusConsumerFactory") ConsumerFactory<String, String> userStatusConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userStatusConsumerFactory);
        return factory;
    }
}
