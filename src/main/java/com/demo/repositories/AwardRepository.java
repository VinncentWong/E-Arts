package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Awards;

@Repository
public interface AwardRepository extends CrudRepository<Awards, Long>{
	
	@Modifying
	@Query(nativeQuery = true, value = "SELECT * FROM awards WHERE artist_id = ?1")
	Optional<List<Awards>> getAwardsByArtistId(Long artistId);
}
