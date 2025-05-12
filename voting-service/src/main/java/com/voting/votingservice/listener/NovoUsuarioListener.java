package com.voting.votingservice.listener;

import com.voting.votingservice.dto.UsuarioCadastradoEvent;
import com.voting.votingservice.model.Voter;
import com.voting.votingservice.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NovoUsuarioListener {

    private final VoterRepository voterRepository;

    @RabbitListener(queues = "${rabbitmq.novousuario.queue}")
    public void novoUsuario(UsuarioCadastradoEvent event) {
        Voter voter = Voter.builder().id(event.id())
                .username(event.username())
                .email(event.email())
                .role(event.role())
                .createdAt(event.createdAt())
                .build();

        voterRepository.save(voter);
    }
}
