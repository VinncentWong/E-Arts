package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.ArtWork;

@Repository
public interface ArtworkRepository extends CrudRepository<ArtWork, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM art_work WHERE artist_id = ?1")
	Optional<List<ArtWork>> getArtworks(Long artistId);
	
	@Query(nativeQuery = true, value = "SELECT *  FROM art_work WHERE artist_id = ?1 AND id = ?2")
	Optional<ArtWork> getArtwork(Long artistId, Long artworkId);
	
	@Query(nativeQuery = true, value = "DELETE FROM art_work WHERE artist_id = ?1 AND id = ?2")
	@Modifying
	void deleteArtwork(Long artistId, Long artworkId);
}
