package com.example.demo.service;

import com.example.demo.persistence.dto.ConnectionRequestDto;
import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.persistence.dto.RoomInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    void create(RoomCreateDto roomDto, Long userId);

    RoomInfoDto getRoomById(Long id);

    Page<RoomInfoDto> getAll(Pageable pageable);

    Page<RoomInfoDto> getAllRoomsByUserId(Long userId, Pageable pageable);

    void connect(Long id, ConnectionRequestDto requestDto);

    void disconnect(Long id, String sid);

    void deleteById(Long id);

}
