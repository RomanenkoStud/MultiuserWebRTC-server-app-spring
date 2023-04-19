package com.example.demo.service.impl;

import com.example.demo.persistence.dto.RoomCreateDto;
import com.example.demo.persistence.model.Room;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.RoomRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public void create(RoomCreateDto roomDto, Long userId) {
        User user = userRepository.getReferenceById(userId);
        Room room = modelMapper.map(roomDto, Room.class);
        room.setUser(user);
        roomRepository.save(room);
    }
}
