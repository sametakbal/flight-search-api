package com.amadeus.flightsearchapi.handler.exceptions;

import java.io.Serial;

public class GeneralException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;
    public GeneralException(String message) {
        super(message);
    }
}
