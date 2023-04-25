package com.example.demo.service;

import com.example.demo.persistence.dto.RoomConnectionDto;
import com.example.demo.persistence.dto.RoomCreateDto;

public interface RoomService {

    void create(RoomCreateDto roomDto, Long userId);

    Long getRoomIdByName(String name);

    void connect(Long id, RoomConnectionDto roomConnectionDto);

    void deleteById(Long id);
}
