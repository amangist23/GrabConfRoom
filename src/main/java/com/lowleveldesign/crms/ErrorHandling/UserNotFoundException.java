package com.lowleveldesign.crms.ErrorHandling;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
