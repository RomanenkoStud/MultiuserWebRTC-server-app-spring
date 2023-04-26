package com.example.demo.persistence.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomInfoDto {
    private Long id;
    private String name;
    private int maxUsers;
    private LocalDateTime dateCreation;
    private boolean isPrivate;
    private List<ConnectedUserDto> connectedUsers;
}
