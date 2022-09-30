package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.domain.dto.UserDto;
import com.demo.exception.UserNotFoundException;
import com.demo.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
    
    private final UserService service;

    @Autowired
    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createUser(@RequestBody @Valid SignUpDto dto){
        return this.service.createUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Valid LoginDto dto){
        return this.service.login(dto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) throws UserNotFoundException{
        return this.service.getUser(id);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<Response> updateUser(@RequestBody @Valid UserDto dto, @PathVariable("userId") Long userId) throws UserNotFoundException{
        return this.service.updateUser(userId, dto);
    }   

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable("userId") Long userId) throws UserNotFoundException{
        return this.service.deleteUser(userId);
    }
}
