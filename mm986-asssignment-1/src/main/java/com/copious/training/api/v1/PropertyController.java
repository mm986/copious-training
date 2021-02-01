package com.copious.training.api.v1;

import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.Label;
import com.copious.training.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @author Mahesh More
 * <p>
 * Controller class to operate on Properties class in java8
 */
@Api(value = "Properties Service Controller")
@RestController
@RequestMapping(value = "/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    /**
     * API to get all System Properties.
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets system properties.", notes = "Gets system properties.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Properties loaded successfully", response = Map.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/system"})
    ResponseEntity<GenericResponse<Map>> getSystemProperties() {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        propertyService.getSystemProperties()
                ),
                HttpStatus.OK
        );
    }

    /**
     * API to get all System Properties.
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets system properties.", notes = "Gets system properties.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Properties loaded successfully", response = Map.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/owner"})
    ResponseEntity<GenericResponse<Label>> getOwnerProperties() {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        propertyService.getOwnerProperties()
                ),
                HttpStatus.OK
        );
    }
}
