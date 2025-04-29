package com.voting.auth.mapper;

import com.voting.auth.dto.RegisterRequest;
import com.voting.auth.dto.RegisterResponse;
import com.voting.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertDtoToUser(RegisterRequest registerRequest);

    RegisterResponse convertUserToDto(User user);

}
