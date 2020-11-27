package com.copious.training.api;

import com.copious.training.api.errors.InternalServerErrorException;
import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.api.errors.ResourceNotFoundException;
import com.copious.training.constants.ExceptionCode;
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

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

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


    private Map error(Object message) {
        return Collections.singletonMap("errors", message);
    }

    public static RuntimeException convert(ExceptionCode status, String message) {
        switch (status) {
            case NOT_FOUND:
                return new ResourceNotFoundException(HttpStatus.NOT_FOUND, message);

            case INVALID_PRODUCT:
                return new InvalidProductException(HttpStatus.BAD_REQUEST, message);

            case INVALID_ORDER:
                return new InvalidOrderException(HttpStatus.BAD_REQUEST, message);

            default:
                return new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, message);
        }
    }
}
