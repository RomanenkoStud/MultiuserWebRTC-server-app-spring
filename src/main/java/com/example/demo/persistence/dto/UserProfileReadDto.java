package com.example.demo.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileReadDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime dateCreation;
    private String imageUrl;
    private String status;
}
