package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.superadmin.SuperAdmin;

@Repository
public interface SuperAdminRepository extends CrudRepository<SuperAdmin, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM super_admin WHERE username = ?1")
    Optional<SuperAdmin> getSuperAdminByUsername(String username);
}
