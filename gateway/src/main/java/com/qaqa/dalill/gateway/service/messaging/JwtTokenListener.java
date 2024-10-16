package com.qaqa.dalill.gateway.service.messaging;

import com.qaqa.dalill.gateway.service.token.TokenService;
import com.qaqa.dalill.gateway.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenListener {

    private final TokenService tokenService;
    private final UserService userService;
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "jwt.token.queue")
    public void generateToken(String username) {
        String token = tokenService.generateToken(username);
        rabbitTemplate.convertAndSend("jwt.token.response.queue", token);
    }

    @RabbitListener(queues = "uuid.queue")
    public void sendUseruuid(String message) {
        String uuid = userService.getUseruuid().toString();
        log.info("Generated UUID: {}", uuid);
        rabbitTemplate.convertAndSend("uuid.reply.queue", uuid);
    }
}
