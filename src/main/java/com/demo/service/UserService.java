package com.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.User;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.repositories.UserRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepo;
	
	private final ResponseUtil util;
	
	private final BCryptPasswordEncoder bcrypt;

	private final JwtUtil<User> jwt;

	@Autowired
	public UserService(UserRepository userRepo, ResponseUtil util, BCryptPasswordEncoder bcrypt, JwtUtil<User> jwt) {
		this.userRepo = userRepo;
		this.util = util;
		this.bcrypt = bcrypt;
		this.jwt = jwt;
	}
	
	public ResponseEntity<Response> createUser(SignUpDto dto){
		Optional<User> tempUser = this.userRepo.getUserByEmail(dto.getEmail());
		if(tempUser.isPresent()){
			return this.util.sendInternalServerError("email sudah ada", false);
		}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setBirthDate(dto.getBirthDate());
		user.setName(dto.getName());
		user.setRole(dto.getRole());
		user.setPassword(bcrypt.encode(dto.getPassword()));
		User userDb = this.userRepo.save(user);
		return this.util.sendCreated("sukses membuat user", true, userDb);
	}

	public ResponseEntity<Response> login(LoginDto dto){
		Optional<User> tempUser = this.userRepo.getUserByEmail(dto.getEmail());
		if(tempUser.isEmpty()){
			return this.util.sendUnauthorized("data user tidak ditemukan!", false);
		}
		User user = tempUser.get();
		if(bcrypt.matches(dto.getPassword(), user.getPassword())){
			String token = this.jwt.generateToken(this.jwt::implementationGenerateToken, user);
			Map<String, Object> map = new HashMap<>();
			map.put("data", user);
			map.put("jwt_token", token);
			return this.util.sendOk("user terautentikasi !", true, map);
		}
		return this.util.sendUnauthorized("user tidak terautentikasi !", false);
	}
}
