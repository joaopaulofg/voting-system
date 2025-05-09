package com.voting.auth.controller;

import com.voting.auth.dto.*;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import com.voting.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserRepository userRepository;

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest data) {
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
        RegisterResponse newUser = authService.register(data);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest data) {
        LoginResponse loginResponse = authService.login(data);
        return ResponseEntity.ok(loginResponse);
    }

    @Operation(summary = "Obter informações do usuário autenticado", description = "Esse endpoint retorna as informações do usuário atualmente autenticado, id, e-mail, username e role.")
    @GetMapping("/me")
    public ResponseEntity<MeResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        var user = (User) userDetails; // cast para sua entidade
        var response = new MeResponse(user.getId(), user.getEmail(), user.getUsername(), user.getRole());
        return ResponseEntity.ok(response);
    }
}
