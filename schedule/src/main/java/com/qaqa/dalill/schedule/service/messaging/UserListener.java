package com.qaqa.dalill.schedule.service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserListener {

    private final RabbitTemplate rabbitTemplate;

    public UUID getUseruuid() {
        rabbitTemplate.convertAndSend("uuid.queue", "request");
        return UUID.randomUUID();
    }
}
