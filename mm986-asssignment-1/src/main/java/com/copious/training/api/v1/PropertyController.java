package com.copious.training.api.v1;

import com.copious.training.domain.Sku;
import com.copious.training.service.PropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(value = {"/property/system"})
    Map getSystemProperties() {
        return propertyService.getSystemProperties();
    }
}
