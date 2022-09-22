package com.demo.domain.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.demo.domain.Role;

import lombok.Data;

@Data
public class SignUpUserDto {

	@Email
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String username;
	
	@NotNull
	@NotBlank
	private String password;
	
	@NotNull
	private Date birthDate;
	
	@NotNull
	private Role role;
}
