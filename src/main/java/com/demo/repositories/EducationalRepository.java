package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Educational;

@Repository
public interface EducationalRepository extends CrudRepository<Educational, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM educational WHERE artist_id_id = ?1")
	Optional<List<Educational>> getEducationalsByArtistId(Long artistId);
}
