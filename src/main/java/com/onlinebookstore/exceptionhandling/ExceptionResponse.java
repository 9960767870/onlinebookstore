package com.onlinebookstore.exceptionhandling;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    public String message;
    public int status;
    public String timestamp;

    public ExceptionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ExceptionResponse(String message, int status, String timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
