package com.example.demo.web.controller;

import com.example.demo.persistence.dto.ConnectionRequestDto;
import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.persistence.dto.RoomInfoDto;
import com.example.demo.service.RoomService;
import com.example.demo.web.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/connect")
    public ResponseEntity<RoomInfoDto> getOne(@RequestParam String name) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roomService.getRoomByName(name));
    }

    @GetMapping
    public Page<RoomInfoDto> getAll(@PageableDefault Pageable pageable) {
        return roomService.getAll(pageable);
    }

    @GetMapping("/{userId}")
    public Page<RoomInfoDto> getAllUserRooms(@PathVariable Long userId, @PageableDefault Pageable pageable) {
        return roomService.getAllRoomsByUserId(userId, pageable);
    }

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
            @RequestBody ConnectionRequestDto connectionRequestDto) {
        roomService.connect(id, connectionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/connect/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disconnect(@PathVariable Long id,
                           @RequestParam String username) {
        roomService.disconnect(id, username);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService.deleteById(id);
    }
}
