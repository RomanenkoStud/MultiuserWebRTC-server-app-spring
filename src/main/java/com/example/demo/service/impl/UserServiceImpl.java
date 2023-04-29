package com.example.demo.service.impl;

import com.example.demo.exception.UserAlreadyExists;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistence.dto.UserCreateDto;
import com.example.demo.persistence.dto.UserProfileReadDto;
import com.example.demo.persistence.dto.UserProfileUpdateDto;
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
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public UserCreateDto addUser(UserCreateDto userCreateDto) {
        if (userRepo.existsByEmail(userCreateDto.getEmail())) {
            throw new UserAlreadyExists("User with this email already exists!");
        }
        userCreateDto.setPassword(encoder.encode(userCreateDto.getPassword()));

        User user = modelMapper.map(userCreateDto, User.class);
        userRepo.save(user);

        return modelMapper.map(user, UserCreateDto.class);
    }

    @Override
    public UserProfileReadDto getById(Long id) {
        User user = findById(id);
        return modelMapper.map(user, UserProfileReadDto.class);
    }

    private User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User does not exist by id: " + id));
    }

    @Override
    @Transactional
    public void updateById(Long id, UserProfileUpdateDto userDto) {
        User user = findById(id);
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getImageUrl() != null) {
            user.setImageUrl(userDto.getImageUrl());
        }
        if (userDto.getPassword() != null) {
            String encodedPassword = encoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);
        }
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
