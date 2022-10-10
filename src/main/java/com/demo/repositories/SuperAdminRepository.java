package com.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.superadmin.SuperAdmin;

@Repository
public interface SuperAdminRepository extends CrudRepository<SuperAdmin, Long>{}
