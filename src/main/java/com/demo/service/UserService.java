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
import com.demo.domain.artist.ArtWork;
import com.demo.domain.artist.Artist;
import com.demo.domain.dto.LoginDto;
import com.demo.domain.dto.SignUpDto;
import com.demo.domain.dto.UserDto;
import com.demo.domain.user.LikedArtwork;
import com.demo.domain.user.User;
import com.demo.exception.ArtistNotFoundException;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.repositories.ArtistRepository;
import com.demo.repositories.ArtworkRepository;
import com.demo.repositories.UserRepository;
import com.demo.util.JwtUtil;
import com.demo.util.ResponseUtil;
import com.demo.util.SetterNullAware;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService {

	private final UserRepository userRepo;
	
	private final ResponseUtil util;
	
	private final BCryptPasswordEncoder bcrypt;

	private final JwtUtil<User> jwt;

	private final ArtistRepository artistRepo;

	private final ArtworkRepository artworkRepo;

	@Autowired
	public UserService(
		UserRepository userRepo, 
		ResponseUtil util, 
		BCryptPasswordEncoder bcrypt, 
		JwtUtil<User> jwt, 
		ArtistRepository artistRepo,
		ArtworkRepository artworkRepo) 
	{
		this.userRepo = userRepo;
		this.util = util;
		this.bcrypt = bcrypt;
		this.jwt = jwt;
		this.artistRepo = artistRepo;
		this.artworkRepo = artworkRepo;
	}
	
	public ResponseEntity<Response> createUser(SignUpDto dto){
		Optional<User> tempUser = this.userRepo.getUserByEmail(dto.getEmail());
		if(tempUser.isPresent()){
			return this.util.sendInternalServerError("email sudah ada", false);
		}
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setBirthDate(dto.getBirthDate());
		user.setUsername(dto.getUsername());
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
		log.info("user role = " + tempUser.get().getRole());
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

	public ResponseEntity<Response> getUser(Long userId) throws UserNotFoundException{
		User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
		return this.util.sendOk("user terautentikasi !", true, user);
	}

	public ResponseEntity<Response> deleteUser(Long userId) throws UserNotFoundException{
		this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
		this.userRepo.deleteById(userId);
		return this.util.sendOk("sukses menghapus data user", true, null);
	}

	public ResponseEntity<Response> updateUser(Long userId, UserDto dto) throws UserNotFoundException{
		User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
		SetterNullAware setter = new SetterNullAware();
		setter.setString(user::setFirstName, dto.getFirstName());
		setter.setString(user::setLastName, dto.getLastName());
		setter.setString(user::setUsername, dto.getUsername());
		setter.setString(user::setPhoneNumber, dto.getPhoneNumber());
		setter.setString(user::setEmail, dto.getEmail());
		setter.setString(user::setPassword, dto.getPassword());
		setter.setGender(user::setGender, dto.getMale());
		this.userRepo.save(user);
		return this.util.sendOk("sukses mengupdate data user", true, null);
	}

	public ResponseEntity<Response> likeArtwork(Long artistId, Long userId, Long artworkId) throws ArtistNotFoundException, UserNotFoundException, ArtworkNotFoundException{
		Artist artist = this.artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
		ArtWork artwork = this.artworkRepo.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		LikedArtwork likedArtwork = new LikedArtwork();
		likedArtwork.setArtwork(artwork);
		likedArtwork.setUser(user);
		artist.setAccumulativeLike(artist.getAccumulativeArtwork() + 1);
		user.getListLikedArtwork().add(likedArtwork);
		this.userRepo.save(user);
		this.artistRepo.save(artist);
		return this.util.sendOk("sukses menambahkan like", true, likedArtwork);
	}

	public ResponseEntity<Response> unlikeArtwork(Long artistId, Long userId, Long artworkId) throws ArtistNotFoundException, UserNotFoundException, ArtworkNotFoundException{
		Artist artist = this.artistRepo.findById(artistId).orElseThrow(() -> new ArtistNotFoundException());
		User user = this.userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());
		ArtWork artwork = this.artworkRepo.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
		LikedArtwork likedArtwork = new LikedArtwork();
		likedArtwork.setArtwork(artwork);
		likedArtwork.setUser(user);
		artist.setAccumulativeLike(artist.getAccumulativeArtwork() - 1);
		user.getListLikedArtwork().remove(likedArtwork);
		this.userRepo.save(user);
		this.artistRepo.save(artist);
		return this.util.sendOk("sukses menghapus like", true, likedArtwork);
	}
}
