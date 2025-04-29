package com.voting.auth.dto;

public record RegisterRequest(
        String nome,
        String sobrenome,
        String cpf,
        String username,
        String password,
        String telefone,
        String email
) {
}
