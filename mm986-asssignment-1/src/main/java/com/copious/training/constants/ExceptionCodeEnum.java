package com.copious.training.constants;

import org.springframework.http.HttpStatus;

/**
 * @author Mahesh More
 * <p>
 * Enumeration  to store all Custom exception codes.
 */
public enum ExceptionCodeEnum {
    NOT_FOUND("Resource Not Found.", HttpStatus.NOT_FOUND, ErrorType.FATAL),
    INVALID_PRODUCT("Invalid Product.", HttpStatus.BAD_REQUEST, ErrorType.FATAL),
    INVALID_ORDER("Invalid Order.", HttpStatus.BAD_REQUEST, ErrorType.FATAL);

    private String message;
    private HttpStatus httpStatus;
    private ErrorType errorType;

    ExceptionCodeEnum(String message, HttpStatus httpStatus, ErrorType errorType) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.errorType = errorType;
    }

    ExceptionCodeEnum() {
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

}
