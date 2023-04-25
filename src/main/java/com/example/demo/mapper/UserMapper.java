package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserCreateDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractConverter<User, UserCreateDto> {
    @Override
    protected UserCreateDto convert(User user) {
        return UserCreateDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
