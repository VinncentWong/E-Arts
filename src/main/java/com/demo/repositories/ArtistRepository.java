package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long>{
	
	/*
	 * Mendapatkan data artis melalui email
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM artist WHERE email = ?1")
	Optional<Artist> getArtistByEmail(String email);
	
	@Query(nativeQuery = true, value = "UPDATE artist SET about = ?1 WHERE id = ?2")
	@Modifying
	Optional<Artist> updateArtistAbout(String about, Long artistId);
	
	@Query(nativeQuery = true, value = "UPDATE artist SET biography = ?1 WHERE id = ?2")
	@Modifying
	void updateArtistBiography(String bio, Long artistId);
	
	@Query(nativeQuery = true, value = "SELECT biography FROM artist WHERE id = ?1")
	@Modifying
	Optional<Artist> getBiographyByArtistId(Long artistId);
}
