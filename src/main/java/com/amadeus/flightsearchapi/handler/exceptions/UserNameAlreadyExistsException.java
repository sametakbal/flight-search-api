package com.amadeus.flightsearchapi.handler.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException(String message) {
        super(message);
    }
}
