package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.domain.Response;
import com.demo.domain.artist.ArtWork;
import com.demo.domain.user.FavoriteArtwork;
import com.demo.domain.user.User;
import com.demo.exception.ArtworkNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.repositories.ArtworkRepository;
import com.demo.repositories.FavoriteArtworkRepository;
import com.demo.repositories.UserRepository;
import com.demo.util.ResponseUtil;

@Service
@Transactional
public class FavoriteArtworkService {

    private final FavoriteArtworkRepository repository;

    private final UserRepository userRepository;

    private final ArtworkRepository artworkRepository;
    
    private final ResponseUtil util;

    @Autowired
    public FavoriteArtworkService(FavoriteArtworkRepository repository, UserRepository userRepository, ArtworkRepository artworkRepository, ResponseUtil util){
        this.repository = repository;
        this.userRepository = userRepository;
        this.artworkRepository = artworkRepository;
        this.util = util;
    }

    public ResponseEntity<Response> createFavoriteArtwork(Long userId, Long artworkId) throws UserNotFoundException, ArtworkNotFoundException{
        FavoriteArtwork favoriteArtwork = new FavoriteArtwork();
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        ArtWork artwork = this.artworkRepository.findById(artworkId).orElseThrow(() -> new ArtworkNotFoundException());
        favoriteArtwork.setUser(user);
        favoriteArtwork.setArtwork(artwork);
        this.repository.save(favoriteArtwork);
        return this.util.sendCreated("sukses membuat favorite artwork", true, favoriteArtwork);
    }
}
