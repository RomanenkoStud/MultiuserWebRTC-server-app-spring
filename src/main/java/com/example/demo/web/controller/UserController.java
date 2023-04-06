package com.example.demo.web.controller;

import com.example.demo.persistence.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        logger.info("Received login request from: email:{}", userDto.getEmail());
        logger.info("Successfully response to: email:{}", userDto.getEmail());
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        logger.info("Received deleteUser request from: id:{}", id);
        return userService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
