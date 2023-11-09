package com.passengermicroservice.exception;

public class PassengerException extends RuntimeException {

    public PassengerException(String message) {
        super(message);
    }

    public PassengerException(String message, Throwable cause) {
        super(message, cause);
    }
}