package com.example.demo.service.impl;

import com.example.demo.exception.RoomConnectionException;
import com.example.demo.exception.RoomNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistence.dto.RoomConnectionCreateDto;
import com.example.demo.persistence.model.Room;
import com.example.demo.persistence.model.RoomUserConnection;
import com.example.demo.persistence.model.RoomUserConnectionId;
import com.example.demo.persistence.model.User;
import com.example.demo.persistence.repository.RoomRepository;
import com.example.demo.persistence.repository.RoomUserConnectionRepository;
import com.example.demo.persistence.repository.UserRepository;
import com.example.demo.service.RoomUserConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomUserConnectionServiceImpl implements RoomUserConnectionService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final RoomUserConnectionRepository connectionRepository;

    @Override
    @Transactional
    public void create(RoomConnectionCreateDto connectionCreateDto) {
        User user = userRepository.findByUsername(connectionCreateDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Cannot find user by username: " + connectionCreateDto.getUsername()));
        Room room = roomRepository.findByName(connectionCreateDto.getRoomName())
                .orElseThrow(() -> new RoomNotFoundException("Cannot find room by name: " + connectionCreateDto.getRoomName()));

        validateRoomConnection(room, connectionCreateDto.getPassword());

        RoomUserConnectionId connectionId = new RoomUserConnectionId(user.getId(), room.getId());

        if (connectionRepository.existsByUserId(user.getId())) {
            throw new RoomConnectionException(String.format("User %s already connected to room %s", user.getUsername(), room.getName()));
        }
        RoomUserConnection connection = new RoomUserConnection();
//        connection.setId(connectionId);
        connection.setUser(user);
        connection.setRoom(room);

        connectionRepository.save(connection);
    }

    private static void validateRoomConnection(Room room, String password) {
        if (room.isPrivate() && !room.getPassword().equals(password)) {
            throw new RoomConnectionException("Connection failed. Wrong password!");
        }
    }
}
