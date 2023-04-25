package com.example.demo.persistence.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomConnectionDto {
    private String username;
    private String password;
}
