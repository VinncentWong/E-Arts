package com.demo.controller;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.OrderDto;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.OrderNotFoundException;
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

    @PostMapping("/create")
    public ResponseEntity<Response> createOrder(@Param("artworkId") Long artworkId, @Param("userId") Long userId, @RequestBody @Valid OrderDto dto) throws ArtworkNotFoundException, UserNotFoundException{
        return this.service.createOrder(artworkId, userId, dto);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> getOrders(@PathVariable("userId") Long userId) throws OrderNotFoundException{
        return this.service.getOrders(userId);
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getOrder(@Param("userId") Long userId, @Param("orderId") Long orderId) throws OrderNotFoundException{
        return this.service.getOrder(userId, orderId);
    }
}
