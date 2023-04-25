package com.example.demo.web.controller;

import com.example.demo.persistence.dto.RoomConnectionDto;
import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.service.RoomService;
import com.example.demo.web.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
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

    @PostMapping("/connect/{id}")
    public ResponseEntity<HttpStatus> connect(
            @PathVariable Long id,
            @RequestBody RoomConnectionDto roomConnectionDto) {
        roomService.connect(id, roomConnectionDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService.deleteById(id);
    }
}
