package com.example.demo.service.impl;

import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistence.dto.UserDto;
import com.example.demo.persistence.dto.UserReadDto;
import com.example.demo.persistence.dto.UserUpdateDto;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
//    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        if (userRepo.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        User user = modelMapper.map(userDto, User.class);
        userRepo.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserReadDto getById(Long id) {
        User user = findById(id);
        return modelMapper.map(user, UserReadDto.class);
    }

    private User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not exist by id: " + id));
    }

    @Override
    @Transactional
    public void updateById(Long id, UserUpdateDto userDto) {
        User user = findById(id);
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setImageUrl(userDto.getImageUrl());
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.delete(user);
                    return true;
                }).orElse(false);
    }
}
