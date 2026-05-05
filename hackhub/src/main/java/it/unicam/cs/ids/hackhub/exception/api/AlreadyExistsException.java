package it.unicam.cs.ids.hackhub.exception.api;

import it.unicam.cs.ids.hackhub.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends ApiException {

    public AlreadyExistsException(){
        super("La risorsa esiste già.");
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.CONFLICT;
    }
}
