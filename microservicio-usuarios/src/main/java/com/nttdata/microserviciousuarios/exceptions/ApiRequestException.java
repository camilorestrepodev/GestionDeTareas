package com.nttdata.microserviciousuarios.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message) {
        super(message);
    }
}