package com.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Artist;
import com.demo.domain.dto.AboutDto;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long>{
	
	/*
	 * Mendapatkan data artis melalui email
	 */
	@Query(nativeQuery = true, value = "SELECT * FROM artist WHERE email = ?1")
	Optional<Artist> getArtistByEmail(String email);
	
	@Query(nativeQuery = true, value = "UPDATE artist SET about = ?1 WHERE id = ?2")
	Optional<Artist> updateArtistAbout(String about, Long artistId);
	
	@Query(nativeQuery = true, value = "UPDATE artist SET biography = ?1 WHERE id = ?2")
	Optional<Artist> createArtistBiography(String bio, Long artistId);
}
