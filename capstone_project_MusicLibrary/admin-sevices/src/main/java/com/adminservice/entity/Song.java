package com.adminservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String singer;
    private String musicDirector;
    private String albumName;
    private String releaseDate;   
    private boolean visible = true;
}
