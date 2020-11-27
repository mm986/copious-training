package com.copious.training.api.v1;

import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Mahesh More
 * <p>
 * Controller class to operate on Orders
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * GET API to get mock Order.
     *
     * @return Order
     * @throws IOException
     */
    @GetMapping(value = {"/order"})
    Order getMockOrder() throws IOException {
        return orderService.getMockOrder();
    }

    /**
     * POST API to validate mock Order.
     *
     * @return Order
     * @throws IOException
     */
    @PostMapping(value = {"/order"})
    Optional<Order> validateOrder(@RequestBody Order order) {
        return orderService.validateOrder(order);
    }
}
