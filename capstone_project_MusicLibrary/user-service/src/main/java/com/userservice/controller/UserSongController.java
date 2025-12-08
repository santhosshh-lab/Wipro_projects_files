package com.userservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.userservice.dto.SongDto;

@RestController
@RequestMapping("/user/songs")
public class UserSongController {

    private final RestTemplate rest = new RestTemplate();
    private final String ADMIN_API = "http://localhost:8082/songs/search?q=";

    @GetMapping("/search")
    public List<SongDto> search(@RequestParam String q) {
    	SongDto[] arr = rest.getForObject(ADMIN_API + q, SongDto[].class);
    	return Arrays.asList(arr);

    }
}
