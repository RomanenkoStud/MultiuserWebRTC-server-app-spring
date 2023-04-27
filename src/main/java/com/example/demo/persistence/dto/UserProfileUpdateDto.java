package com.example.demo.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdateDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    private String status;
    private String password;
    private String imageUrl;
}

