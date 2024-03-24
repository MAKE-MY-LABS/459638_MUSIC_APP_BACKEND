package com.example.spotifyapp.serviceImpl;

/* create the following methods
 * 1. validate user by checking the credentials  , if the emailId and password are correct return the user object
 * otherwise return null
 * 2. save user to the database  check if the user alraedy exists throw  UserAlreadyExistException
 *
 * autowire the UserRepository
 */

import com.example.spotifyapp.Model.User;
import com.example.spotifyapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.spotifyapp.exception.UserAlreadyExistException;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User validateUser(String emailId, String password) {
        Optional<User> op = userRepository.findByEmailIdAndPassword(emailId, password);
        if(op.isPresent()) {
            return op.get();
        }
        return null;
    }

    public User saveUser(User user) throws UserAlreadyExistException {
        Optional<User>  op = userRepository.findByEmailId(user.getEmailId());
        if(op.isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }
        return userRepository.save(user);
    }
}
