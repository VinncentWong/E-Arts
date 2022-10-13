package com.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.Response;
import com.demo.domain.superadmin.dto.LoginDto;
import com.demo.exception.AdminNotFoundException;
import com.demo.exception.SuperAdminNotFoundException;
import com.demo.service.SuperAdminService;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {
    
    private final SuperAdminService service;

    @Autowired
    public SuperAdminController(SuperAdminService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createSuperAdmin(@RequestBody @Valid LoginDto dto){
        return this.service.createSuperAdmin(dto);
    } 

    @PostMapping("/login")
    public ResponseEntity<Response> loginSuperAdmin(@RequestBody @Valid LoginDto dto) throws SuperAdminNotFoundException{
        return this.service.loginSuperAdmin(dto);
    }

    @PostMapping("/createadmin")
    public ResponseEntity<Response> createAdmin(com.demo.domain.admin.dto.LoginDto dto){
        return this.service.createAdmin(dto);
    }

    @GetMapping("/getadmin/{id}")
    public ResponseEntity<Response> getAdmin(@PathVariable("id") Long id) throws AdminNotFoundException{
        return this.service.getAdmin(id);
    }

    @GetMapping("/getadmins")
    public ResponseEntity<Response> getAdmins(){
        return this.service.getAdmins();
    }

}
