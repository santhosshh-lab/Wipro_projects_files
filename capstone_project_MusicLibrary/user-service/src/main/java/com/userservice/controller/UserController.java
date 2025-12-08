
package com.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.dto.SongDto;
import com.userservice.entity.User;
import com.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User u) {
        return service.register(u);
    }

    // ✔ LOGIN USING JSON BODY
    @PostMapping("/login")
    public User login(@RequestBody User req) {
        return service.login(req.getEmail(), req.getPassword());
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User u) {
        return service.update(id, u);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted successfully";
    }
    // USER — GET ALL AVAILABLE SONGS
    @GetMapping("/songs")
    public List<SongDto> getAllSongs() {
        return service.getAllSongs();
    }

    // USER — GET ONE SONG
    @GetMapping("/songs/{id}")
    public SongDto getSong(@PathVariable Long id) {
        return service.getSongById(id);
    }

}




