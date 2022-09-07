package com.demo.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AwardsDto {

	@NotNull
	@NotBlank
	private String awards;
}
