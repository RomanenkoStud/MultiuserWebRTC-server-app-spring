package com.example.demo.service;

import com.example.demo.persistence.dto.UserCreateDto;
import com.example.demo.persistence.dto.UserProfileReadDto;
import com.example.demo.persistence.dto.UserProfileUpdateDto;

public interface UserService {
    UserCreateDto addUser(UserCreateDto userCreateDto);
    UserProfileReadDto getById(Long id);

    void updateById(Long id, UserProfileUpdateDto userDto);
    boolean deleteById(long id);
}
