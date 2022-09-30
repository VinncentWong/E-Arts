package com.demo.controller;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.OrderDto;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService){
        this.service = orderService;
    }

    public ResponseEntity<Response> createOrder(@Param("artworkId") Long artworkId, @Param("userId") Long userId, @RequestBody @Valid OrderDto dto) throws ArtworkNotFoundException, UserNotFoundException{
        return this.service.createOrder(artworkId, userId, dto);
    }
}
