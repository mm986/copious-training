package com.copious.training.api.errors;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {
    private final HttpStatus status;

    public InternalServerErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return this.status;
    }
}
