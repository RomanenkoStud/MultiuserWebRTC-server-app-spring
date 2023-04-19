package com.example.demo.mapper;

import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.persistence.model.Room;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoomCreateDtoMapper extends AbstractConverter<RoomCreateDto, Room> {
    @Override
    protected Room convert(RoomCreateDto roomDto) {
        return Room.builder()
                .name(roomDto.getName())
                .isPrivate(roomDto.isPrivate())
                .password(roomDto.getPassword())
                .numberOfUsers(roomDto.getNumberOfUsers())
                .build();
    }
}
