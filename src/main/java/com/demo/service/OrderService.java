package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.ArtWork;
import com.demo.domain.dto.OrderDto;
import com.demo.domain.user.Order;
import com.demo.domain.user.User;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.OrderNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.repositories.ArtworkRepository;
import com.demo.repositories.OrderRepository;
import com.demo.repositories.UserRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;

    private final ArtworkRepository artworkRepository;

    private final UserRepository userRepository;

    private final ResponseUtil util;

    @Autowired
    public OrderService(OrderRepository orderRepository, ArtworkRepository artworkRepository, UserRepository userRepository, ResponseUtil util){
        this.orderRepository = orderRepository;
        this.artworkRepository = artworkRepository;
        this.userRepository = userRepository;
        this.util = util;
    }

    public ResponseEntity<Response> createOrder(Long artworkId, Long userId, OrderDto dto) throws ArtworkNotFoundException, UserNotFoundException{
        ArtWork artwork = this.artworkRepository.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Order order = new Order();
        order.setArtwork(artwork);
        order.setUser(user);
        order.setQuantity(dto.getQuantity());
        order.setTotalPrice(dto.getTotalPrice());
        this.orderRepository.save(order);
        return this.util.sendCreated("sukses membuat order", true, order);
    }

    public ResponseEntity<Response> getOrders(Long userId) throws OrderNotFoundException{
        List<Order> orders = this.orderRepository.getOrders(userId).orElseThrow(() -> new OrderNotFoundException());
        return this.util.sendOk("sukses menemukan data order", true, orders);
    }

    public ResponseEntity<Response> getOrder(Long userId, Long orderId) throws OrderNotFoundException{
        Order order = this.orderRepository.getOrder(userId, orderId).orElseThrow(() -> new OrderNotFoundException());
        return this.util.sendOk("sukses menemukan data order", true, order);
    }
}
