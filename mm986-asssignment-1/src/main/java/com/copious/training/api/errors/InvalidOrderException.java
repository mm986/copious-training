package com.copious.training.api.errors;

import com.copious.training.constants.ExceptionCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author Mahesh More.
 * <p>
 * Defining custom exception to handle Invalid Order Exception.
 */
public class InvalidOrderException extends RuntimeException {

    private static final Long serialVersionId = 123456L;

    private final ExceptionCodeEnum exceptionCodeEnum;

    private final String errorDetails;

    public InvalidOrderException(ExceptionCodeEnum exceptionCodeEnum, String message, String errorDetails) {
        super(message);
        this.exceptionCodeEnum = exceptionCodeEnum;
        this.errorDetails = errorDetails;
    }

    public InvalidOrderException(ExceptionCodeEnum code, String message, Throwable throwable) {
        super(message, throwable);
        this.exceptionCodeEnum = code;
        this.errorDetails = message;
    }

    public ExceptionCodeEnum getCode() {
        return exceptionCodeEnum;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    /**
     * Intended to get Http status of exception.
     *
     * @return HttpStatus
     */
    public HttpStatus getStatus() {
        return exceptionCodeEnum.getHttpStatus();
    }
}

