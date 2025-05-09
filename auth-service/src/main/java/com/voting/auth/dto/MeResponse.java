package com.voting.auth.dto;

import com.voting.auth.model.Role;

public record MeResponse(Long id, String email, String username, Role role) {
}
