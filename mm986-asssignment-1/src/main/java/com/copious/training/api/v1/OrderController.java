package com.copious.training.api.v1;

import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * @author Mahesh More
 * <p>
 * Controller class to operate on Orders
 */
@Api(value = "Order Service Controller")
@RestController
@RequestMapping(value = "/order")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * GET API to get Order By OrderId.
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
    @GetMapping("/{orderId}")
    ResponseEntity<GenericResponse<Order>> getOrderById(@PathVariable(name = "orderId") String orderId) throws IOException, InterruptedException {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.getOrderById(orderId)),
                HttpStatus.OK
        );
    }

    /**
     * GET API to get all Orders.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Gets all orders available in system.", notes = "Gets all orders available in system.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order loaded successfully", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping
    ResponseEntity<GenericResponse<List<Order>>> getOrders() throws IOException, InterruptedException {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.getOrders()),
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
    ResponseEntity<GenericResponse<Optional<Order>>> validateOrder(@RequestBody Order order) {
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.validateOrder(order)),
                HttpStatus.OK
        );
    }

    /**
     * GET API to Load Order Sequentially.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Loads Order Sequentially.", notes = "Loads Order Sequentially.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order loaded successfully", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping("/sequential")
    ResponseEntity<GenericResponse<Order>> loadOrderSequentially() throws IOException, InterruptedException {
        log.info("Sequential - Started loading Order. Time - {}", LocalDateTime.now());
        ResponseEntity responseEntity = new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.loadMockOrderSequentially()),
                HttpStatus.OK
        );
        log.info("Sequential - Order loaded successfully. Time - {}", LocalDateTime.now());

        return responseEntity;
    }

    /**
     * GET API to Load Order Parallelly.
     *
     * @return Order
     * @throws IOException
     */
    @ApiOperation(value = "Loads Order Parallelly.", notes = "Loads Order Parallelly.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The Order loaded successfully", response = Order.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Response.class),
            @ApiResponse(code = 404, message = "Resource not found", response = Response.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Response.class),
    })
    @GetMapping("/parallel")
    ResponseEntity<GenericResponse<Order>> loadOrderParallel() throws IOException, ExecutionException, InterruptedException {
        log.info("Parallel - Started loading Order. Time - {}", LocalDateTime.now());
        ResponseEntity responseEntity = new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        orderService.loadMockOrderParallelly()),
                HttpStatus.OK
        );
        log.info("Parallel - Order loaded successfully. Time - {}", LocalDateTime.now());
        return responseEntity;
    }
}
