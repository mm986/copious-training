package com.copious.training.api.v1;

import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.Sku;
import com.copious.training.service.AsyncService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/async")
@Log4j2
@CrossOrigin
public class AsyncController {

    @Autowired
    AsyncService asyncService;


    @ApiOperation(value = "Demonstration of Async Service.", notes = "Demonstration of Async Service.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Items loaded successfully", response = Sku.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping
    public ResponseEntity<GenericResponse<String>> callAsync() {
        final String correlationId = UUID.randomUUID().toString();
        try {
            asyncService.asyncServiceDemo();
        } catch (InterruptedException | ExecutionException exception) {
            log.error("Async service failed with Exception: {} {}", exception.getMessage(), exception.getStackTrace());
        }

        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(), "Async Service started successfully:" + correlationId
                ),
                HttpStatus.OK
        );
    }
}
