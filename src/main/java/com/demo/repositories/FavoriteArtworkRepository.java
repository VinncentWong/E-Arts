package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.user.FavoriteArtwork;

@Repository
public interface FavoriteArtworkRepository extends CrudRepository<FavoriteArtwork, Long>{}
