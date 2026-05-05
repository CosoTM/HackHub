package it.unicam.cs.ids.hackhub.exception.api;

import it.unicam.cs.ids.hackhub.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenOperationException extends ApiException {

    public ForbiddenOperationException(String message){
        super(message);
    }
    public ForbiddenOperationException() {
        super("Operazione non permessa");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
