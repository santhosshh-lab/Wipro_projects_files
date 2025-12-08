package com.userservice.repo;

import com.userservice.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistSongRepository extends JpaRepository<PlaylistSong, Long> {
    List<PlaylistSong> findByPlaylistId(Long playlistId);
    void deleteByPlaylistIdAndSongId(Long playlistId, Long songId);
}
