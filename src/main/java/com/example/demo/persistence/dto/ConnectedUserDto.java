package com.example.demo.persistence.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ConnectedUserDto {
    private String username;
    private String imageUrl;
}
