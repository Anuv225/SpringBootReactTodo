package com.todo.todo.exception;

import java.time.LocalDateTime;

public class ErrorRresponse {

    private LocalDateTime timestamp;

    private String message;

    private String details;

    public ErrorRresponse(String message, LocalDateTime timestamp, String details) {

        this.message = message;
        this.timestamp = timestamp;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
