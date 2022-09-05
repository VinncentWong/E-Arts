package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.SocialMedia;

@Repository
public interface SocialMediaRepository extends CrudRepository<SocialMedia, Long>{}
