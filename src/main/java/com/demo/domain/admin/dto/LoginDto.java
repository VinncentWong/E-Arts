package com.demo.domain.admin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDto {
    
    @NotNull
    @NotBlank
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String password;
}
