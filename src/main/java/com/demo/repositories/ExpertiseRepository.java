package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Expertise;

@Repository
public interface ExpertiseRepository extends CrudRepository<Expertise, Long> {
	
	@Query(nativeQuery = true, value = "SELECT * FROM expertise WHERE artist_id = ?1")
	Optional<List<Expertise>> getExpertisesByArtistId(Long artistId);
	
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE expertise SET expertise.expertise = ?1 WHERE id = ?2")
	void updateExpertiseData(String text, Long expertiseId);
}
