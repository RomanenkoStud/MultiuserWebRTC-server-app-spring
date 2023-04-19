package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractConverter<User, UserDto> {
    @Override
    protected UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
