package com.example.demo.persistence.dto;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomCreateDto {

    @NotEmpty
    private String name;

    private boolean isPrivate;

    private String password;

    @Min(1L)
    private int numberOfUsers;
}
