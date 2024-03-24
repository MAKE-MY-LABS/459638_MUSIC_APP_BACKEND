package com.example.spotifyapp.repository;

import com.example.spotifyapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIdAndPassword(String emailId, String password);

    Optional<User> findByEmailId(String emailId);
}