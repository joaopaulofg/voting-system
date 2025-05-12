package com.voting.auth.service;

import com.voting.auth.model.User;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(User user, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", user);
    }

}
