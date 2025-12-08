package com.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adminservice.entity.Song;
import com.adminservice.service.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService service;

    // CREATE
    @PostMapping
    public Song add(@RequestBody Song s) {
        return service.add(s);
    }

    // READ ALL
    @GetMapping
    public List<Song> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Song getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Song update(@PathVariable Long id, @RequestBody Song s) {
        return service.update(id, s);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // GET ONLY VISIBLE SONGS
    @GetMapping("/visible")
    public List<Song> getVisible() {
        return service.getVisibleSongs();
    }

    // CHANGE VISIBILITY
    @PatchMapping("/{id}/visibility")
    public Song setVisibility(
            @PathVariable Long id,
            @RequestParam boolean visible
    ) {
        return service.setVisibility(id, visible);
    }
    @GetMapping("/search")
    public List<Song> search(@RequestParam String q) {
        return service.searchSongs(q);
    }

}
