package com.example.spotifyapp.repository;

import com.example.spotifyapp.Model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * SongRepository is an interface that extends JpaRepository
 */
@Repository
public interface SongRepository extends JpaRepository<Song, String> {

}


