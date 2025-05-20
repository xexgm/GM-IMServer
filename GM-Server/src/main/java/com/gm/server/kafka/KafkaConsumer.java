package com.gm.server.kafka;

import com.gm.link.common.domain.model.RedisOperationMessage;
import com.gm.link.common.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

/**
 * @Author: hhj023
 * @Date: 2025/5/13
 * @Description: 处理 光芒Link 上行kafka消息
 */
@Component
public class KafkaConsumer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    // todo 主题从公共包里取

    // 监听 singleChat 主题
    @KafkaListener(topics = "private_chat", errorHandler = "kafkaErrorHandler")
    public void consumeSingleChat(List<String> messages) {

    }

    // 监听 groupChat 主题
    @KafkaListener(topics = "group_chat", errorHandler = "kafkaErrorHandler")
    public void consumeGroupChat(List<String> messages) {

    }


    /* 上线、心跳、下线 -> 操作redis 主题 */
    @KafkaListener(topics = "link_topic",
            containerFactory = "statusFactory",
            errorHandler = "kafkaErrorHandler")
    public void consumeStatusServer(String message) {
        RedisOperationMessage opMessage = JsonUtil.fromJson(message, RedisOperationMessage.class);
        switch (opMessage.getOp()) {
            case "SET":

                break;
            case "SETNX":
                String userId = opMessage.getKey();
                String machineId = opMessage.getValue();
                Integer expireSeconds = opMessage.getExpireSeconds();

                // 操作redis
                Boolean success = redisTemplate.opsForValue().setIfAbsent(
                        userId,
                        machineId,
                        Duration.ofSeconds(expireSeconds)
                );
                // 成功处理逻辑
                break;
            case "DELETE":

                break;
        }
    }

    // todo 自定义异常处理器
    @Bean
    public ConsumerAwareListenerErrorHandler kafkaErrorHandler() {
        return (message, exception, consumer) -> {

            return null;
        };
    }
}
