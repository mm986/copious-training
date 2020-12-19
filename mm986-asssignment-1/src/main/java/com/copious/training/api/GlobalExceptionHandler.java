package com.copious.training.api;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.api.errors.ResourceNotFoundException;
import com.copious.training.domain.ImmutableError;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mahesh More.
 * <p>
 * Defining Global Exception Handler to handle multiple business exceptions
 * related to Product and Order.
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    /**
     * Handler to handle and convert MethodArgumentTypeMismatchException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(MethodArgumentTypeMismatchException exception) {
        return error(Stream.of(exception)
                .map(e -> ImmutableError.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message(e.getMessage())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * Handler to handle and convert MethodArgumentNotValidException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(MethodArgumentNotValidException exception) {
        return error(exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> ImmutableError.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message(e.getDefaultMessage())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * Handler to handle and convert InvalidOrderException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(InvalidOrderException exception) {
        return error(Stream.of(exception)
                .map(e -> ImmutableError.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message(e.getMessage())
                        .build())
                .collect(Collectors.toList()));
    }

//    @ExceptionHandler(value = {InvalidOrderException.class})
//    private ResponseEntity<Object> handle(InvalidOrderException exception, WebRequest webRequest) {
//
//    }

    /**
     * Handler to handle and convert InvalidProductException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(InvalidProductException exception) {
        return error(Stream.of(exception)
                .map(e -> ImmutableError.builder()
                        .code(HttpStatus.BAD_REQUEST.toString())
                        .message(e.getMessage())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * Handler to handle and convert ResourceNotFoundException
     *
     * @param exception
     * @return Exception Map
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map handle(ResourceNotFoundException exception) {
        return error(Stream.of(exception)
                .map(e -> ImmutableError.builder()
                        .code(HttpStatus.NOT_FOUND.toString())
                        .message(e.getMessage())
                        .build())
                .collect(Collectors.toList()));
    }

    /**
     * Intended to convert Exceptions to Exception Map.
     *
     * @param message
     * @return
     */
    private Map error(Object message) {
        return Collections.singletonMap("errors", message);
    }

}
