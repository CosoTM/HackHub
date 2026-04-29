package it.unicam.cs.ids.hackhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import it.unicam.cs.ids.hackhub.exception.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException e){
        ErrorResponse error = new ErrorResponse(
                e.getMessage(),
                e.getStatus().value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, e.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionFallback(Exception e){
        ErrorResponse error = new ErrorResponse(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

