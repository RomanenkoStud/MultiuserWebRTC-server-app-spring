package com.example.demo.service;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.dto.UserReadDto;
import com.example.demo.persistence.dto.UserUpdateDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    UserReadDto getById(Long id);

    void updateById(Long id, UserUpdateDto userDto);
    boolean deleteById(long id);
}
