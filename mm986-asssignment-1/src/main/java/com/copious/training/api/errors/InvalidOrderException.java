package com.copious.training.api.errors;

import org.springframework.http.HttpStatus;

/**
 * @author Mahesh More.
 * <p>
 * Defining custom exception to handle Invalid Order Exception.
 */
public class InvalidOrderException extends RuntimeException {

    private final HttpStatus status;

    public InvalidOrderException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    /**
     * Intended to get Http status of exception.
     *
     * @return HttpStatus
     */
    public HttpStatus getStatus() {
        return this.status;
    }
}

