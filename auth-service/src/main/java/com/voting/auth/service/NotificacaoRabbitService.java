package com.voting.auth.service;

import com.voting.auth.dto.UsuarioCadastradoEvent;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificacaoRabbitService {

    private final RabbitTemplate rabbitTemplate;

    public void notificar(UsuarioCadastradoEvent user, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", user);
    }

}
