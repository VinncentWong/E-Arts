package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.domain.user.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM address WHERE id = ?2 AND user_id = ?1")
    Optional<Address> getOneAddress(Long userId, Long addressId);
}
