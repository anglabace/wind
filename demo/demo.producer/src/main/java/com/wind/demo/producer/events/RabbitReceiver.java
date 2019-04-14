package com.wind.demo.producer.events;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitReceiver {

    public void handleMessage(String message) {
        log.info("Rabbit Receive: {}", message);
    }

    @Bean
    MessageListenerAdapter mqListenerAdapter() {
        return new MessageListenerAdapter(this);
    }

}