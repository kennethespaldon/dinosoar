package com.dinosoar.backend.exception;

public class FlightNotFoundException extends ResourceNotFoundException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
