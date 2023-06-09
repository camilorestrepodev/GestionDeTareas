package com.nttdata.microserviciotareas.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }
}