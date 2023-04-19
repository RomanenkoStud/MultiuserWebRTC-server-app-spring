package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper extends AbstractConverter<UserDto, User> {
    @Override
    protected User convert(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .build();
    }
}
