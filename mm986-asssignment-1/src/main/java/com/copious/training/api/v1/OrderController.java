package com.copious.training.api.v1;

import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Mahesh More
 * <p>
 * Controller class to operate on Orders
 */
@Api(value = "Order Service Controller")
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * GET API to get mock Order.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Gets mock order available in system.", notes = "Gets mock order available in system.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order loaded successfully", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping
    ResponseEntity<GenericResponse<Order>> getMockOrder() throws IOException {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.getMockOrder()),
                HttpStatus.OK
        );
    }

    /**
     * POST API to validate mock Order.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Validates order against business rules defined.", notes = "Validates order against business rules defined.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order validated successfully", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @PostMapping
    Optional<Order> validateOrder(@RequestBody Order order) {
        return orderService.validateOrder(order);
    }
}
