package com.voting.auth.dto;

import com.voting.auth.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank @Pattern(regexp = "\\d{11}") String cpf,
        @NotBlank String username,
        @NotBlank String password,
        String telefone,
        @Email String email,
        @NotBlank Role role
) {
}
