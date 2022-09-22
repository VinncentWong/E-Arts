package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.artist.ArtWork;
import com.demo.domain.artist.Photo;

@Repository
public interface ArtworkRepository extends CrudRepository<ArtWork, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM art_work WHERE artist_id = ?1")
	Optional<List<ArtWork>> getArtworks(Long artistId);
	
	@Query(nativeQuery = true, value = "SELECT *  FROM art_work WHERE artist_id = ?1 AND id = ?2")
	Optional<ArtWork> getArtwork(Long artistId, Long artworkId);
	
	@Query(nativeQuery = true, value = "DELETE FROM art_work WHERE artist_id = ?1 AND id = ?2")
	@Modifying
	void deleteArtwork(Long artistId, Long artworkId);
	
	@Query(nativeQuery = true, value = "DELETE FROM art_work_weight_dimension WHERE id = ?1")
	@Modifying
	void deleteArtworkWeight(Long artworkWeightId);
	
	@Query(nativeQuery = true, value = "DELETE FROM dimension WHERE id = ?1")
	@Modifying
	void deleteDimension(Long dimensionId);
	
	@Query(nativeQuery = true, value = "UPDATE FROM photo SET photo = data WHERE id = ?2 AND artwork_id=?1")
	@Modifying
	void updatePhoto(Long artworkId, Long photoId, byte[] data);
	
	@Query(nativeQuery = true, value = "SELECT * FROM photo WHERE id = ?1")
	Optional<Photo> getPhoto(Long photoId);
}
