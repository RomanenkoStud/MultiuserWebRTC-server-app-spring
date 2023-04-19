package com.example.demo.service;

import com.example.demo.persistence.dto.RoomCreateDto;

public interface RoomService {

    void create(RoomCreateDto roomDto, Long userId);
}
