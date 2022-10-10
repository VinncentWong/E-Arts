package com.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.Role;

import com.demo.domain.admin.entity.Admin;
import com.demo.domain.superadmin.SuperAdmin;
import com.demo.domain.superadmin.dto.LoginDto;
import com.demo.exception.AdminNotFoundException;
import com.demo.exception.SuperAdminNotFoundException;
import com.demo.repositories.AdminRepository;
import com.demo.repositories.SuperAdminRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class SuperAdminService {
    
    private final AdminRepository repository;

    private final BCryptPasswordEncoder bcrypt;

    private final SuperAdminRepository sAdminRepository;

    private final ResponseUtil util;

    private final JwtUtil<SuperAdmin> jwt;

    @Autowired
    public SuperAdminService(
        AdminRepository repository, 
        BCryptPasswordEncoder bcrypt, 
        ResponseUtil util, 
        SuperAdminRepository sAdmin,
        JwtUtil<SuperAdmin> jwt
    )
    {
        this.repository = repository;
        this.bcrypt = bcrypt;
        this.util = util;
        this.sAdminRepository = sAdmin;
        this.jwt = jwt;
    }

    public ResponseEntity<Response> createSuperAdmin(SuperAdmin sAdmin){
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUsername(sAdmin.getUsername());
        superAdmin.setPassword(bcrypt.encode(sAdmin.getPassword()));
        superAdmin.setRole(Role.SUPERADMIN);
        return this.util.sendCreated("sukses membuat super admin", true, superAdmin);
    }

    public ResponseEntity<Response> login(LoginDto dto) throws SuperAdminNotFoundException{
        SuperAdmin superAdmin = this.sAdminRepository.getSuperAdminByUsername(dto.getUsername()).orElseThrow(() -> new SuperAdminNotFoundException());
        if(bcrypt.matches(dto.getPassword(),superAdmin.getPassword())){
            String token = jwt.generateToken(jwt::implementationGenerateToken, superAdmin);
            Map<String, Object> map = new HashMap<>();
            map.put("data", superAdmin);
            map.put("jwt_token", token);
            return this.util.sendOk("sukses login", true, map);
        }
        return this.util.sendUnauthorized("gagal login, terjadi kesalahan : user tidak terautentikasi", false);
    }

    public ResponseEntity<Response> createAdmin(com.demo.domain.admin.dto.LoginDto dto){
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
