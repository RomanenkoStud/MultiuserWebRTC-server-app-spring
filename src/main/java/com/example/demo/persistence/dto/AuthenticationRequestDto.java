package com.example.demo.persistence.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequestDto {

    @NotBlank
    @Pattern(regexp = ".+@.+\\..+")
    private String email;

    @NotBlank
    @Size(min = 3)
    private String password;
}
