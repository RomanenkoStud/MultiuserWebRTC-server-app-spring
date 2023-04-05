package com.example.demo.mapper;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
