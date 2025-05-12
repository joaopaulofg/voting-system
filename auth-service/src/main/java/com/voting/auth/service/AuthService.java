package com.voting.auth.service;

import com.voting.auth.dto.*;
import com.voting.auth.mapper.UserMapper;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import com.voting.auth.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    private final NotificacaoRabbitService notificacaoRabbitService;

    private final String exchangeNovoUsuario;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,
                       TokenService tokenService, NotificacaoRabbitService notificacaoRabbitService, @Value("${rabbitmq.novousuario.exchange}") String exchangeNovoUsuario) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.notificacaoRabbitService = notificacaoRabbitService;
        this.exchangeNovoUsuario = exchangeNovoUsuario;
    }

    public RegisterResponse register(RegisterRequest data) {
        User user = UserMapper.INSTANCE.convertDtoToUser(data);
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);

        var evento = new UsuarioCadastradoEvent(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                LocalDateTime.now()
        );

        notificarRabbitMQ(evento);

        return UserMapper.INSTANCE.convertUserToDto(user);
    }

    private void notificarRabbitMQ(UsuarioCadastradoEvent user) {
        try{
            notificacaoRabbitService.notificar(user, exchangeNovoUsuario);
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
