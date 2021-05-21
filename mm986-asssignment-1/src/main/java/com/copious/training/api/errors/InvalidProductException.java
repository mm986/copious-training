package com.copious.training.api.errors;

import com.copious.training.constants.ExceptionCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @author Mahesh More.
 * <p>
 * Defining custom exception to handle Invalid Product Exception.
 */
public class InvalidProductException extends RuntimeException {

    private static final Long serialVersionId = 123456L;

    private final ExceptionCodeEnum exceptionCodeEnum;

    private final String errorDetails;

    public InvalidProductException(ExceptionCodeEnum exceptionCodeEnum, String message, String errorDetails) {
        super(message);
        this.exceptionCodeEnum = exceptionCodeEnum;
        this.errorDetails = errorDetails;
    }

    public InvalidProductException(ExceptionCodeEnum code, String message, Throwable throwable) {
        super(message, throwable);
        this.exceptionCodeEnum = code;
        this.errorDetails = message;
    }

    /**
     * Intended to get ExceptionCodeEnum of custom exception.
     *
     * @return HttpStatus
     */
    public ExceptionCodeEnum getExceptionCodeEnum() {
        return exceptionCodeEnum;
    }

    /**
     * Intended to get ErrorDetails.
     *
     * @return ErrorDetails String
     */
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

