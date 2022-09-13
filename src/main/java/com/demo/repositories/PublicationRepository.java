package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Publications;

@Repository
public interface PublicationRepository extends CrudRepository<Publications, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM publications")
	public Optional<List<Publications>> getPublications(Long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM publications WHERE artist_id = ?1 AND id = ?2")
	public Optional<Publications> getPublication(Long artistId, Long publicationId);
}
