package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Publications;
import com.demo.domain.dto.PublicationDto;

@Repository
public interface PublicationRepository extends CrudRepository<Publications, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM publications")
	public Optional<List<Publications>> getPublications(Long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM publications WHERE artist_id = ?1 AND id = ?2")
	public Optional<Publications> getPublication(Long artistId, Long publicationId);
	
	@Query(nativeQuery = true, value = "UPDATE publications SET publications = ?3 WHERE artist_id = ?1 AND id = ?2")
	public void updatePublication(Long artistId, Long publicationId, String publications);
	
	@Query(nativeQuery = true, value = "DELETE FROM publications WHERE id = ?2 AND artist_id = ?1")
	public void deletePublication(Long artistId, Long publicationId);
}