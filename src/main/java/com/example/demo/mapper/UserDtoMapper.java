package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserCreateDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import static com.example.demo.persistence.model.enums.Role.USER;

@Component
public class UserDtoMapper extends AbstractConverter<UserCreateDto, User> {
    @Override
    protected User convert(UserCreateDto userCreateDto) {
        return User.builder()
                .email(userCreateDto.getEmail())
                .username(userCreateDto.getUsername())
                .password(userCreateDto.getPassword())
                .role(USER)
                .build();
    }
}
