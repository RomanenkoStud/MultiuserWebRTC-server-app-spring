package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserReadDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserReadDtoMapper extends AbstractConverter<User, UserReadDto> {
    @Override
    protected UserReadDto convert(User user) {
        return UserReadDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .dateCreation(user.getDateCreation())
                .imageUrl(user.getImageUrl())
                .status(user.getStatus().toString())
                .build();
    }
}
