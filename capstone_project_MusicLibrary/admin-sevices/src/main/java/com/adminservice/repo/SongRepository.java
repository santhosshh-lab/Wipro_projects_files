package com.adminservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adminservice.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByVisibleTrue();
    
    @Query("SELECT s FROM Song s WHERE " +
    	       "LOWER(s.name) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
    	       "LOWER(s.singer) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
    	       "LOWER(s.albumName) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
    	       "LOWER(s.musicDirector) LIKE LOWER(CONCAT('%', :q, '%'))")
    	List<Song> searchSongs(String q);

}
