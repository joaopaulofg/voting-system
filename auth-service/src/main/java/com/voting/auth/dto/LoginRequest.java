package com.voting.auth.dto;

public record LoginRequest(
        String username,
        String password
) {
}
