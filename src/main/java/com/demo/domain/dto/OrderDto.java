package com.demo.domain.dto;

import javax.validation.constraints.NotNull;

import com.demo.domain.artist.ArtWork;
import com.demo.domain.user.User;

import lombok.Data;

@Data
public class OrderDto {
    
    @NotNull
    private User user;

    @NotNull
    private ArtWork artwork;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer totalPrice;
}
