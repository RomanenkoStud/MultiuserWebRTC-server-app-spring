package com.example.demo.mapper;

import com.example.demo.persistence.dto.RoomInfoDto;
import com.example.demo.persistence.model.Room;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class RoomInfoDtoMapper extends AbstractConverter<Room, RoomInfoDto> {
    @Override
    protected RoomInfoDto convert(Room room) {
        return RoomInfoDto.builder()
                .id(room.getId())
                .name(room.getName())
                .maxUsers(room.getNumberOfUsers())
                .dateCreation(room.getDateCreation())
                .isPrivate(room.isPrivate())
                .build();
    }
}
