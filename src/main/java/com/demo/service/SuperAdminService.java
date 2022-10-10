package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.Role;
import com.demo.domain.admin.dto.LoginDto;
import com.demo.domain.admin.entity.Admin;
import com.demo.exception.AdminNotFoundException;
import com.demo.repositories.AdminRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class SuperAdminService {
    
    private final AdminRepository repository;

    private final BCryptPasswordEncoder bcrypt;

    private final ResponseUtil util;

    @Autowired
    public SuperAdminService(AdminRepository repository, BCryptPasswordEncoder bcrypt, ResponseUtil util){
        this.repository = repository;
        this.bcrypt = bcrypt;
        this.util = util;
    }

    public ResponseEntity<Response> createAdmin(LoginDto dto){
        Admin admin = new Admin();
        admin.setPhoneNumber(dto.getPhoneNumber());
        admin.setRole(Role.ADMIN);
        admin.setPassword(bcrypt.encode(dto.getPassword()));
        this.repository.save(admin);
        return this.util.sendCreated("sukses membuat admin", true, admin);
    }

    public ResponseEntity<Response> getAdmin(Long id) throws AdminNotFoundException{
        Admin admin = this.repository.findById(id).orElseThrow(() -> new AdminNotFoundException());
        return this.util.sendOk("sukses mendapatkan admin", true, admin);
    }

    public ResponseEntity<Response> getAdmins(){
        return this.util.sendOk("sukses mendapatkan semua data admin", true, this.repository.findAll());
    }
}
