package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.PersonalInformation;

@Repository
public interface PersonalInformationRepository extends CrudRepository<PersonalInformation, Long>{
	/*
	 * Get personal information data by artist id
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM personal_information WHERE artist_id = ?1")
	Optional<PersonalInformation> getPersonalInformationByArtistId(Long id);
	
	@Query(nativeQuery = true, value = "DELETE FROM personal_information WHERE artist_id = ?1 AND id = ?2")
	void deletePersonalInformation(Long artistId, Long id);
}
