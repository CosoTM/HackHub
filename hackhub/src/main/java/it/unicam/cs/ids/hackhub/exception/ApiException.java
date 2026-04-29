package it.unicam.cs.ids.hackhub.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException{
    private final HttpStatus status;

    public ApiException(String message){
        super(message);
        this.status = getStatus();
    }

    public abstract HttpStatus getStatus();
}
