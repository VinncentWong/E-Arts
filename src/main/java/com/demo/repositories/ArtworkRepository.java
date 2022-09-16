package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.ArtWork;

@Repository
public interface ArtworkRepository extends CrudRepository<ArtWork, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM artwork WHERE artist_id = ?1")
	Optional<List<ArtWork>> getArtwork(Long artistId);
}
