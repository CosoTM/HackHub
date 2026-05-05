package it.unicam.cs.ids.hackhub.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errorDetails;

    public ErrorResponse(String message, int statusCode, LocalDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        errorDetails = null;
    }

    public ErrorResponse(String message, int statusCode, LocalDateTime timeStamp, Map<String, String> errorDetails) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
        this.errorDetails = errorDetails;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public Map<String, String> getErrorDetails() {return errorDetails;}
}
