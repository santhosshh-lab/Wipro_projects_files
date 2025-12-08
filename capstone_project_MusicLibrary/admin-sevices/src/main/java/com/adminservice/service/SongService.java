package com.adminservice.service;

import com.adminservice.entity.Song;
import com.adminservice.repo.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository repo;

    // CREATE
    public Song add(Song s) {
        return repo.save(s);   // INSERT into songs ...
    }

    // READ ALL
    public List<Song> getAll() {
        return repo.findAll(); // SELECT * from songs
    }

    // READ BY ID
    public Song getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    // UPDATE
    public Song update(Long id, Song newSong) {
        Song old = repo.findById(id).orElseThrow();

        old.setName(newSong.getName());
        old.setSinger(newSong.getSinger());
        old.setMusicDirector(newSong.getMusicDirector());
        old.setAlbumName(newSong.getAlbumName());
        old.setReleaseDate(newSong.getReleaseDate());
        old.setVisible(newSong.isVisible());

        return repo.save(old); // UPDATE songs ...
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);   // DELETE from songs where id=?
    }

    // GET ONLY VISIBLE SONGS
    public List<Song> getVisibleSongs() {
        return repo.findByVisibleTrue();
    }

    // TOGGLE VISIBILITY
    public Song setVisibility(Long id, boolean visible) {
        Song s = repo.findById(id).orElseThrow();
        s.setVisible(visible);
        return repo.save(s);
    }
    public List<Song> searchSongs(String q) {
        return repo.searchSongs(q);
    }
}
