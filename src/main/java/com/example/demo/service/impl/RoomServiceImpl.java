package com.example.demo.service.impl;

import com.example.demo.exception.RoomConnectionException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.persistence.dto.ConnectedUserDto;
import com.example.demo.persistence.dto.RoomConnectionDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final Map<Long, List<ConnectedUserDto>> connections = new ConcurrentHashMap<>();
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

    @Override
    public Long getRoomIdByName(String name) {
        return roomRepository.findByName(name)
                .orElseThrow(() -> new RoomNotFoundException("Can not find room by name: " + name)).getId();
    }

    @Override
    public void connect(Long id, RoomConnectionDto roomConnectionDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Can not find room by id: " + id));
        validateRoomConnection(room, roomConnectionDto.getPassword());

        List<ConnectedUserDto> connectedUsers = connections.computeIfAbsent(id, key -> new ArrayList<>());
        if (connectedUsers.size() < room.getNumberOfUsers()) {
            ConnectedUserDto connectedUser = createConnectedUser(roomConnectionDto.getUsername());
            connectedUsers.add(connectedUser);
        } else {
            throw new RoomConnectionException("Connection failed! Room is full!");
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException("Can not find room by id: " + id));
        roomRepository.delete(room);
    }

    private ConnectedUserDto createConnectedUser(String username) {
        String imageUrl = loadImageUrlByUsername(username);
        return ConnectedUserDto.builder().username(username).imageUrl(imageUrl).build();
    }

    private String loadImageUrlByUsername(String username) {
        return userRepository.findByUsername(username).map(User::getImageUrl).orElse(null);
    }

    private static void validateRoomConnection(Room room, String password) {
        if (room.isPrivate() && !room.getPassword().equals(password)) {
            throw new RoomConnectionException("Connection failed! Wrong password!");
        }
    }
}
