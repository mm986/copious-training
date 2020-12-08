package com.copious.training.api.errors;

import org.springframework.http.HttpStatus;

/**
 * @author Mahesh More.
 * <p>
 * Defining custom exception to handle Resource Not Found Exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public ResourceNotFoundException(HttpStatus status, String message) {
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