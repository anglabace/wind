package com.wind.demo.producer.task;

import com.wind.demo.producer.events.RabbitSender;
import com.wind.demo.producer.events.RedisSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private RedisSender redisSender;

    @Autowired
    private RabbitSender rabbitSender;

    @Scheduled(fixedRate = 13000)
    public void sendMessageToRedis() {
        redisSender.send("chat", "Hello To Redis!");
    }

    @Scheduled(fixedRate = 17000)
    public void sendMessageToBus() {
        rabbitSender.send("rabbit", "Hello To RabbitMQ!");
    }
}