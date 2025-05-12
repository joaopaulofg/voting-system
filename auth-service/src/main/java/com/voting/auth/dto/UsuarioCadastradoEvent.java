package com.voting.auth.dto;

import java.time.LocalDateTime;

public record UsuarioCadastradoEvent(
        Long id,
        String username,
        String email,
        String role,
        LocalDateTime createdAt
) {}
