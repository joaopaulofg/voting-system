package com.voting.votingservice.dto;

import java.time.LocalDateTime;

public record UsuarioCadastradoEvent(
        Long id,
        String username,
        String email,
        String role,
        LocalDateTime createdAt
) {}