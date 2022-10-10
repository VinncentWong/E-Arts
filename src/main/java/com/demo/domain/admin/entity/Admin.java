package com.demo.domain.admin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.demo.domain.Role;

import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String phoneNumber;

    private String password;

    private String createdAt;

    private String updatedAt;

    private Role role;
}
