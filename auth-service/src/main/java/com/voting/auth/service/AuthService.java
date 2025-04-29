package com.voting.auth.service;

import com.voting.auth.dto.RegisterRequest;
import com.voting.auth.dto.RegisterResponse;
import com.voting.auth.mapper.UserMapper;
import com.voting.auth.model.User;
import com.voting.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private UserRepository userRepository;

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = UserMapper.INSTANCE.convertDtoToUser(registerRequest);
        userRepository.save(user);

        return UserMapper.INSTANCE.convertUserToDto(user);
    }

}
