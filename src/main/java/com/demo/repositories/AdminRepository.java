package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.admin.entity.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long>{}
