    package com.example.spotifyapp.exception;

    /*
     * UserAlreadyExistException extends Exception   * create the following fields
     */
    public class UserAlreadyExistException extends Throwable {
        public UserAlreadyExistException(String message) {
            super(message);
        }
    }
