package com.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.artist.SocialMedia;

@Repository
public interface SocialMediaRepository extends CrudRepository<SocialMedia, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM social_media WHERE artist_id = ?1")
	public Optional<List<SocialMedia>> getSocialMedias(Long artistId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM social_media WHERE artist_id = ?1 AND id = ?2")
	public Optional<SocialMedia> getSocialMedia(Long artistId, Long socialMediaId);
	
	@Query(nativeQuery = true, value = "UPDATE social_media SET social_media = ?3 WHERE id = ?2 AND artist_id = ?1")
	@Modifying
	public void updateSocialMedia(Long artistId, Long socialMediaId, String socialMedia);
	
	@Query(nativeQuery = true, value = "DELETE FROM social_media WHERE artist_id = ?1 AND id = ?2")
	@Modifying
	public void deleteSocialMedia(Long artistId, Long socialMediaId);
}
