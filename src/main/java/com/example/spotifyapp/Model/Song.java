package com.example.spotifyapp.Model;

/*
 * create the following fields
 * songId,songName,language,favouriteCount
 * use Lombok to generate no-args constructor,All Argument constructor, getters, setters, and toString() method.
 * Use @Document annotation to specify the collection name in mysql.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Song {

    @Id
    private String songId;
    private String songName;
    private String language;
    private int favouriteCount;


}
