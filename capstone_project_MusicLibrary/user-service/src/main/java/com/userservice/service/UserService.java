package com.userservice.service;

import com.userservice.entity.User;
import org.springframework.web.client.RestTemplate;
import com.userservice.dto.SongDto;
import java.util.Arrays;
import java.util.List;

import com.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    

    private final String ADMIN_SONG_URL = "http://localhost:8082/songs";

    // REGISTER USER
    public User register(User u) {
    	return repo.save(u);   // store plain password

    }

    // LOGIN USER
    public User login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email not found"));

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }


        return user;
    }

    // GET ALL USERS
    public List<User> getAll() {
        return repo.findAll();
    }

    // GET USER BY ID
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE USER
    public User update(Long id, User updated) {
        User existing = getById(id);

        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());

        // If password changed → encode again
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(updated.getPassword());
        }

        return repo.save(existing);
    }

    // DELETE USER
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // GET ALL SONGS FROM ADMIN SERVICE
    public List<SongDto> getAllSongs() {
        SongDto[] songs = restTemplate.getForObject(ADMIN_SONG_URL, SongDto[].class);
        return Arrays.asList(songs);
    }

    // GET SONG BY ID
    public SongDto getSongById(Long id) {
        return restTemplate.getForObject(ADMIN_SONG_URL + "/" + id, SongDto.class);
    }
}
