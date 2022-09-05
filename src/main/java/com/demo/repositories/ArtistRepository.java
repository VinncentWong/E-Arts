package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.domain.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Long>{}
