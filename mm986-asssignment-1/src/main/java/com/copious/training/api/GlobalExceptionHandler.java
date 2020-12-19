package com.copious.training.api;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.api.errors.ResourceNotFoundException;
import com.copious.training.constants.ExceptionCodeEnum;
import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.ImmutableError;
import com.copious.training.domain.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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
//    @ExceptionHandler
//    public GenericResponse handle(InvalidOrderException exception) {
//
//
////        return error(Stream.of(exception)
////                .map(e -> ImmutableError.builder()
////                        .code(e.getCode().getHttpStatus().getReasonPhrase())
////                        .message(e.getMessage())
////                        .errorType(e.getCode().getErrorType().name())
////                        .build())
////                .collect(Collectors.toList()));
//    }

    @ExceptionHandler(value = {InvalidOrderException.class})
    private ResponseEntity<Object> handle(InvalidOrderException exception, WebRequest webRequest) {
        Response response = new Response();
        response.setMessage(ExceptionCodeEnum.INVALID_ORDER.getMessage());

        GenericResponse<Response> genericResponse =
                new GenericResponse<>(false,
                        ExceptionCodeEnum.INVALID_ORDER.getHttpStatus().name(),
                        response);

        return handleExceptionInternal(exception,
                genericResponse,
                new HttpHeaders(),
                ExceptionCodeEnum.INVALID_ORDER.getHttpStatus(),
                webRequest);
    }

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
