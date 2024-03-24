package com.example.spotifyapp.Controller;

import com.example.spotifyapp.Filter.Filter;
import com.example.spotifyapp.exception.UserAlreadyExistException;
import com.example.spotifyapp.serviceImpl.UserService;
import com.example.spotifyapp.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/*
*create respective end points for the methods in userService
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws UserAlreadyExistException {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateUser(@RequestBody User user) {
        User user1 = userService.validateUser(user.getEmailId(), user.getPassword());
        if (user1 == null) {
            return new ResponseEntity<String>("User is invalid", HttpStatus.NOT_FOUND);
        }
        String token = getToken(user.getEmailId());
        return new ResponseEntity<String>("User logged in successfully !! Token:" + token, HttpStatus.OK);
    }

    // create method gettoken to generate token based on emailId
    public String getToken(String emailId) {
        return Jwts.builder().setSubject(emailId).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60000))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, jwtSigningKey).compact();
    }



}
