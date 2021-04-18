package com.qlassalle.elementsrecorder.domain.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("Unable to find user with email " + email);
    }
}
