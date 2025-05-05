package com.voting.auth.dto;

import com.voting.auth.model.Role;

public record RegisterRequest(
        String nome,
        String sobrenome,
        String cpf,
        String username,
        String password,
        String telefone,
        String email,
        Role role
) {
}
