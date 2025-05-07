package com.voting.auth.controller;

import com.voting.auth.dto.*;
import com.voting.auth.repository.UserRepository;
import com.voting.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}
