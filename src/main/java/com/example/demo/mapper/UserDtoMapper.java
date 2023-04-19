package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import static com.example.demo.persistence.model.Role.USER;
import static com.example.demo.persistence.model.UserStatus.ACTIVE;

@Component
public class UserDtoMapper extends AbstractConverter<UserDto, User> {
    @Override
    protected User convert(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(USER)
                .status(ACTIVE)
                .build();
    }
}
