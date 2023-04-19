package com.example.demo.service.impl;

import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.persistence.model.Role.*;
import static com.example.demo.persistence.model.UserStatus.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
//    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto addUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }
        return Optional.of(userDto)
//                        .map(userMapper::toEntity)
                .map(dto -> modelMapper.map(dto, User.class))
                        .map(user -> {
                            user.setPassword(encoder.encode(user.getPassword()));
                            user.setRole(USER);
                            user.setStatus(ACTIVE);
                            return userRepo.save(user);
                        })
//                .map(userMapper::toDto)
                .map(user -> modelMapper.map(user, UserDto.class))
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
