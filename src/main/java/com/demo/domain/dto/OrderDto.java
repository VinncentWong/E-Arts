package com.demo.domain.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDto {

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer totalPrice;
}
