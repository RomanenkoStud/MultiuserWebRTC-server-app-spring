package com.example.demo.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomConnectionCreateDto {
    @NotNull
    private String username;
    @NotNull
    private String roomName;
    private String password;
}
