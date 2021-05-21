package com.copious.training.api;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.api.errors.ResourceNotFoundException;
import com.copious.training.constants.ExceptionCodeEnum;
import com.copious.training.domain.ErrorResponse;
import com.copious.training.domain.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Mahesh More.
 * <p>
 * Defining Global Exception Handler to handle multiple business exceptions
 * related to Product and Order.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler to handle and convert InvalidOrderException
     *
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = {InvalidOrderException.class})
    private ResponseEntity<Object> handle(InvalidOrderException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,
                new GenericResponse<>(false,
                        ExceptionCodeEnum.INVALID_ORDER.getHttpStatus().name(),
                        new ErrorResponse(ExceptionCodeEnum.INVALID_ORDER.getMessage(),
                                exception.getErrorDetails()
                        )),
                new HttpHeaders(),
                ExceptionCodeEnum.INVALID_ORDER.getHttpStatus(),
                webRequest
        );
    }

    /**
     * Handler to handle and convert InvalidProductException
     *
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(value = {InvalidProductException.class})
    private ResponseEntity<Object> handle(InvalidProductException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,
                new GenericResponse<>(false,
                        ExceptionCodeEnum.INVALID_PRODUCT.getHttpStatus().name(),
                        new ErrorResponse(ExceptionCodeEnum.INVALID_PRODUCT.getMessage(),
                                exception.getErrorDetails()
                        )),
                new HttpHeaders(),
                ExceptionCodeEnum.INVALID_PRODUCT.getHttpStatus(),
                webRequest
        );
    }

    /**
     * Handler to handle and convert ResourceNotFoundException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    public ResponseEntity<Object> handle(ResourceNotFoundException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,
                new GenericResponse<>(false,
                        ExceptionCodeEnum.NOT_FOUND.getHttpStatus().name(),
                        new ErrorResponse(ExceptionCodeEnum.NOT_FOUND.getMessage(),
                                exception.getMessage()
                        )),
                new HttpHeaders(),
                ExceptionCodeEnum.NOT_FOUND.getHttpStatus(),
                webRequest
        );
    }

    /**
     * Handler to handle and convert MethodArgumentTypeMismatchException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    public ResponseEntity<Object> handle(MethodArgumentTypeMismatchException exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,
                new GenericResponse<>(false,
                        ExceptionCodeEnum.BAD_REQUEST.getHttpStatus().name(),
                        new ErrorResponse(ExceptionCodeEnum.BAD_REQUEST.getMessage(),
                                exception.getMessage()
                        )),
                new HttpHeaders(),
                ExceptionCodeEnum.BAD_REQUEST.getHttpStatus(),
                webRequest
        );
    }

    /**
     * Handler to handle and convert Internal Server Error
     *
     * @param exception
     * @return Exception Map
     */
    public ResponseEntity<Object> handle(Exception exception, WebRequest webRequest) {
        return handleExceptionInternal(exception,
                new GenericResponse<>(false,
                        ExceptionCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus().name(),
                        new ErrorResponse(exception.getMessage(), exception.getMessage())),
                new HttpHeaders(),
                ExceptionCodeEnum.INTERNAL_SERVER_ERROR.getHttpStatus(),
                webRequest
        );
    }
}
