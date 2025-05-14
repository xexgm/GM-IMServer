package com.gm.server.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: hhj023
 * @Date: 2025/5/13
 * @Description: 处理 光芒Link 上行kafka消息
 */
@Component
public class KafkaConsumer {

    // todo 主题从公共包里取

    // 监听 singleChat 主题
    @KafkaListener(topics = "privateChat", groupId = "gm-server-group", errorHandler = "kafkaErrorHandler")
    public void consumeSingleChat(List<String> messages) {

    }

    // 监听 groupChat 主题
    @KafkaListener(topics = "groupChat", groupId = "gm-server-group", errorHandler = "kafkaErrorHandler")
    public void consumeGroupChat(List<String> messages) {

    }


    /* todo 上线、心跳、下线 -> 操作redis 主题 */


    // todo 自定义异常处理器
    @Bean
    public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
        return (message, exception, consumer) -> {

            return null;
        };
    }
}
