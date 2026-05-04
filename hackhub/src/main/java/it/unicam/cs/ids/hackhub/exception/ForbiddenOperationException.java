package it.unicam.cs.ids.hackhub.exception;

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
