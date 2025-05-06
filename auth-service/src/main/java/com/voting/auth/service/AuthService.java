package com.voting.auth.service;

import com.voting.auth.dto.RegisterResponse;
import com.voting.auth.mapper.UserMapper;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return UserMapper.INSTANCE.convertUserToDto(user);
    }

}
