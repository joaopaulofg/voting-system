package com.voting.auth.dto;

public record RegisterResponse(
        Long id,
        String username,
        String email
) {
}
