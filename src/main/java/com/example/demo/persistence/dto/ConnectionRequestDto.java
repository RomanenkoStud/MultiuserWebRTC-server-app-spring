package com.example.demo.persistence.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConnectionRequestDto {
    private String username;
    private String password;
}
