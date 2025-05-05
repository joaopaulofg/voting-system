package com.voting.auth.controller;

import com.voting.auth.dto.RegisterRequest;
import com.voting.auth.dto.RegisterResponse;
import com.voting.auth.mapper.UserMapper;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import com.voting.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity authenticate(@RequestBody RegisterRequest data) {
        if(this.userRepository.findByUsername(data.username()) != null) return ResponseEntity.badRequest().build();
        User user = UserMapper.INSTANCE.convertDtoToUser(data);
        RegisterResponse newUser = authService.register(user);
        return ResponseEntity.ok(newUser);
    }
}
