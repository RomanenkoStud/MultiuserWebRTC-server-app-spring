package com.example.demo.web.controller;

import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.service.RoomService;
import com.example.demo.web.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<HttpStatus> create(
            @RequestBody RoomCreateDto roomDto,
            @AuthenticationPrincipal UserDetailsImpl currentUser) {
        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        roomService.create(roomDto, currentUser.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
