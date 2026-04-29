package it.unicam.cs.ids.hackhub.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timeStamp;

    public ErrorResponse(String message, int statusCode, LocalDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = timeStamp;
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
}
