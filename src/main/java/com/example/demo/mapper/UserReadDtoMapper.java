package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserProfileReadDto;
import com.example.demo.persistence.model.User;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class UserReadDtoMapper extends AbstractConverter<User, UserProfileReadDto> {
    @Override
    protected UserProfileReadDto convert(User user) {
        return UserProfileReadDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .dateCreation(user.getDateCreation())
                .imageUrl(user.getImageUrl())
                .status(user.getStatus())
                .build();
    }
}
