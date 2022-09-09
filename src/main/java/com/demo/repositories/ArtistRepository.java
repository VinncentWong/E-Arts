package com.demo.repositories;

import java.util.Optional;

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
	
	@Query(nativeQuery = true, value = "SELECT about FROM artist WHERE id = ?1")
	Optional<Artist> getArtistAbout(Long artistId);
}
