package com.nttdata.microserviciousuarios.exceptions;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }
}
