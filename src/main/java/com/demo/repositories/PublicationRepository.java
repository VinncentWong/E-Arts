package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Publications;

@Repository
public interface PublicationRepository extends CrudRepository<Publications, Long>{}
