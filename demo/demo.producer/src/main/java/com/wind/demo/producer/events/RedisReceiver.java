package com.wind.demo.producer.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisReceiver {

    public void handleMessage(String message) {
        log.info("Redis Receive <{}>", message);
    }

    @Bean
    MessageListenerAdapter redisListenerAdapter() {
        return new MessageListenerAdapter(this);
    }
}