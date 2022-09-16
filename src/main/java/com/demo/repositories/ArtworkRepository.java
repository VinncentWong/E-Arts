package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.ArtWork;

@Repository
public interface ArtworkRepository extends CrudRepository<ArtWork, Long> {}
