package com.example.demo.service;

import com.example.demo.persistence.dto.ConnectionRequestDto;
import com.example.demo.persistence.dto.DisconnectionRequestDto;
import com.example.demo.persistence.dto.RoomCreateDto;

public interface RoomService {

    void create(RoomCreateDto roomDto, Long userId);

    Long getRoomIdByName(String name);

    void connect(Long id, ConnectionRequestDto requestDto);

    void disconnect(DisconnectionRequestDto requestDto);

    void deleteById(Long id);
}
