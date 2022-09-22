package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.artist.Educational;

@Repository
public interface EducationalRepository extends CrudRepository<Educational, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM educational WHERE artist_id_id = ?1")
	Optional<List<Educational>> getEducationalsByArtistId(Long artistId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM educational WHERE artist_id_id=?1 AND id = ?2")
	Optional<Educational> getEducationalByArtistId(Long artistId, Long educationalId);
	
	@Query(nativeQuery = true, value = "UPDATE educational SET education=?3 WHERE artist_id_id = ?1 AND id = ?2")
	@Modifying
	void updateEducational(Long artistId, Long educationalId, String educational);
	
	@Query(nativeQuery = true, value = "DELETE FROM educational WHERE artist_id_id = ?1 AND id = ?2")
	@Modifying
	void deleteEducational(Long artistId, Long educationalId);
}
