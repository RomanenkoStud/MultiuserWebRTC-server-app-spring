package com.example.demo.web.controller;

import com.example.demo.persistence.dto.UserCreateDto;
import com.example.demo.persistence.dto.UserProfileReadDto;
import com.example.demo.persistence.dto.UserProfileUpdateDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UserCreateDto> register(@RequestBody UserCreateDto userCreateDto) {
            logger.info("Received login request from: email:{}", userCreateDto.getEmail());
            logger.info("Successfully response to: email:{}", userCreateDto.getEmail());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.addUser(userCreateDto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileReadDto> getUserById(@PathVariable Long id) {
        logger.info("Received deleteUser request from: id:{}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> updateById(@PathVariable Long id, @RequestBody UserProfileUpdateDto userDto) {
        userService.updateById(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).build();
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
