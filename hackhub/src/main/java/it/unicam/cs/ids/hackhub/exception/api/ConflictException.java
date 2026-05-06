package it.unicam.cs.ids.hackhub.exception.api;

import it.unicam.cs.ids.hackhub.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException {

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException() {
        super("Conflitto di stato");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
