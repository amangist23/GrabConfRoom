package com.lowleveldesign.crms.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.sql.Timestamp;

public class ErrorResponse {
    private final String error;
    private final String message;

    private final HttpStatus status;
    private final Timestamp timestamp;

    public ErrorResponse(String error, String message, HttpStatus httpStatus) {
        this.error = error;
        this.message = message;
        this.status = httpStatus;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
