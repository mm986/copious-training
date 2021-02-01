package com.copious.training.api.v1;

import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.Sku;
import com.copious.training.entity.Item;
import com.copious.training.service.ProductService;
import io.swagger.annotations.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Controller class to perform CRUD on available Items in Order
 */
@Api(value = "Order Item Service Controller")
@RestController
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ProductService productService;

    /**
     * API to get Items by Product Category
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets items by category.", notes = "Gets items by category.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Items loaded successfully", response = Sku.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/by/category"})
    public ResponseEntity<GenericResponse<List<Item>>> getItemsByCategory(
            @ApiParam(value = "Category of the Item.", example = "HEALTH_AND_WELLNESS", name = "sku")
            @RequestParam(value = "sku", required = true) String sku
    ) {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        productService.getItemsByCategory(sku)
                ),
                HttpStatus.OK
        );
    }
}
