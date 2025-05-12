package com.voting.auth.service;

import com.voting.auth.dto.LoginRequest;
import com.voting.auth.dto.LoginResponse;
import com.voting.auth.dto.RegisterRequest;
import com.voting.auth.dto.RegisterResponse;
import com.voting.auth.mapper.UserMapper;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import com.voting.auth.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String exchange = "novo-usuario.ex";

    public RegisterResponse register(RegisterRequest data) {
        User user = UserMapper.INSTANCE.convertDtoToUser(data);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        notificarRabbitMQ(user);

        return UserMapper.INSTANCE.convertUserToDto(user);
    }

    private void notificarRabbitMQ(User user) {
        try{
            notificacaoRabbitService.notificar(user, exchange);
        }catch (RuntimeException e){
            System.out.println("Erro ao notificar");
        }
    }

    public LoginResponse login(@Valid LoginRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponse(token);
    }
}
