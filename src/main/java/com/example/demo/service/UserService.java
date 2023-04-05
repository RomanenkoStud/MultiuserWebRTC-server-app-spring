package com.example.demo.service;

import com.example.demo.persistence.dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto userDto);
    boolean deleteById(long id);
}
