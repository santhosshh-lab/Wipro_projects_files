package com.userservice.dto;

import lombok.Data;

@Data
public class SongDto {
    private Long id;
    private String name;
    private String singer;
    private String musicDirector;
    private String albumName;
    private String releaseDate;
    private boolean visible;
}
