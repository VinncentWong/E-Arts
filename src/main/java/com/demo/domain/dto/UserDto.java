package com.demo.domain.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.domain.Gender;

import lombok.Data;

@Data
public class UserDto {
    
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender male;

    // optional
    private String firstName;

    private String lastName;
}