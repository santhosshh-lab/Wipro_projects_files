package com.userservice.service;

import com.userservice.dto.SongDto;
import com.userservice.entity.Playlist;
import com.userservice.entity.PlaylistSong;
import com.userservice.repo.PlaylistRepository;
import com.userservice.repo.PlaylistSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepo;

    @Autowired
    private PlaylistSongRepository playlistSongRepo;

    @Autowired
    private RestTemplate restTemplate;

    private final String SONG_API = "http://localhost:8082/songs/";

    // CREATE
    public Playlist createPlaylist(Long userId, Playlist p) {
        p.setUserId(userId);
        return playlistRepo.save(p);
    }

    // READ ALL
    public List<Playlist> getUserPlaylists(Long userId) {
        return playlistRepo.findByUserId(userId);
    }

    // UPDATE
    public Playlist updatePlaylist(Long userId, Long playlistId, Playlist updated) {
        Playlist old = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!old.getUserId().equals(userId))
            throw new RuntimeException("Not your playlist");

        old.setName(updated.getName());
        old.setDescription(updated.getDescription());
        return playlistRepo.save(old);
    }

    // DELETE
    public String deletePlaylist(Long userId, Long playlistId) {
        Playlist p = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!p.getUserId().equals(userId))
            throw new RuntimeException("Not your playlist");

        playlistSongRepo.deleteAll(playlistSongRepo.findByPlaylistId(playlistId));
        playlistRepo.delete(p);

        return "Playlist deleted";
    }

    
 // ADD SONG TO PLAYLIST WITH VALIDATION
    public PlaylistSong addSong(Long userId, Long playlistId, Long songId) {

        if (songId == null) {
            throw new RuntimeException("songId cannot be null");
        }

        Playlist playlist = playlistRepo.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        if (!playlist.getUserId().equals(userId)) {
            throw new RuntimeException("This playlist does not belong to the user");
        }

        // CHECK IF SONG EXISTS IN ADMIN-SERVICE
        try {
            SongDto song = restTemplate.getForObject(SONG_API + songId, SongDto.class);
            if (song == null || song.getId() == null) {
                throw new RuntimeException("Song does not exist in Admin-Service");
            }
        } catch (Exception ex) {
            throw new RuntimeException("Invalid Song ID: Song not found in Admin-Service");
        }

        // CHECK DUPLICATE ENTRY
        List<PlaylistSong> existingSongs = playlistSongRepo.findByPlaylistId(playlistId);
        boolean alreadyExists = existingSongs.stream()
                .anyMatch(ps -> ps.getSongId().equals(songId));

        if (alreadyExists) {
            throw new RuntimeException("Song is already added to this playlist");
        }

        // SAVE SONG TO PLAYLIST
        PlaylistSong ps = new PlaylistSong();
        ps.setPlaylistId(playlistId);
        ps.setSongId(songId);

        return playlistSongRepo.save(ps);
    }


    // REMOVE SONG
    public String removeSong(Long playlistId, Long songId) {
        playlistSongRepo.deleteByPlaylistIdAndSongId(playlistId, songId);
        return "Song removed from playlist";
    }

    // GET SONGS INSIDE PLAYLIST
    public List<SongDto> getSongsInPlaylist(Long playlistId) {
        List<PlaylistSong> list = playlistSongRepo.findByPlaylistId(playlistId);

        return list.stream()
                .map(ps -> {
                    try {
                        return restTemplate.getForObject(SONG_API + ps.getSongId(), SongDto.class);
                    } catch (Exception e) {
                        System.out.println("Song not found for ID: " + ps.getSongId());
                        return null;
                    }
                })
                .filter(song -> song != null)
                .toList();
    }

}
