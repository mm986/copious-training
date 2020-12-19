package com.copious.training.api.v1;

import com.copious.training.constants.ProductCategory;
import com.copious.training.domain.Sku;
import com.copious.training.service.ProductService;
import io.swagger.annotations.*;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Mahesh More
 * <p>
 * Controller class to perform CRUD on available Products for Order
 */
@Api(value = "Product Service Controller")
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * API to get Products by Product Category
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets products by category.", notes = "Gets products by category.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Products loaded successfully", response = Sku.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/by/category"})
    List<Sku> getProductsByCategory(
            @ApiParam(value = "Category of the Product.", example = "2018-01-01", name = "category")
            @RequestParam(value = "category", required = true) ProductCategory category
    ) throws IOException {
        return productService.getProducts(category);
    }

    /**
     * API to get mock Products [ArrayList Implementation].
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets mock Products available in system.", notes = "Gets mock Products available in system.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Products loaded successfully", response = Sku.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/with/arraylist"})
    List<Sku> getMockProductsFromArrayList() throws IOException {
        return productService.getProductListFromArrayList();
    }

    /**
     * API to get mock Products [LinkedList Implementation].
     *
     * @return Sku's
     * @throws IOException
     */
    @ApiOperation(value = "Gets mock Products available in system.", notes = "Gets mock Products available in system.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Products loaded successfully", response = Sku.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/with/linkedlist"})
    List<Sku> getMockProductsFromLinkedList() throws IOException {
        return productService.getProductListFromLinkedList();
    }

    /**
     * API to get expensive Product from list of available Products.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Gets most expensive Product available in system.", notes = "Gets most expensive Product available in system.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product loaded successfully", response = Sku.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping(value = {"/expensive"})
    Sku getMockProduct() throws IOException {
        return productService.getExpensiveProduct();
    }

    /**
     * POST API to validate mock Product.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Validates Product as per the business rules.", notes = "Validates Product as per the business rules.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Product validated successfully", response = Sku.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @PostMapping
    Optional<Sku> validateProduct(@RequestBody Sku product) {
        return productService.validateProduct(product);
    }
}
