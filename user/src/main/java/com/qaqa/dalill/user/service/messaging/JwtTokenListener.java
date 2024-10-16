package com.qaqa.dalill.user.service.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenListener {

    private final RabbitTemplate rabbitTemplate;

    public String getToken(String email) {
        rabbitTemplate.convertAndSend("jwt.token.queue", email);
        return (String)rabbitTemplate.receiveAndConvert("jwt.token.response.queue");
    }

}
