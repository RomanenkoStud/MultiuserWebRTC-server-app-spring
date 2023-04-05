package com.example.demo.service.impl;

import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.mapper.UserMapper;
import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.Role;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }
        return Optional.of(userDto)
                        .map(userMapper::toEntity)
                        .map(user -> {
                            user.setPassword(encoder.encode(user.getPassword()));
                            user.setRole(Role.USER);
                            return userRepo.save(user);
                        })
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Override
    public boolean deleteById(long id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.delete(user);
                    return true;
                }).orElse(false);
    }
}
