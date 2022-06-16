package com.example.learning.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String message) { super(message);}
}
