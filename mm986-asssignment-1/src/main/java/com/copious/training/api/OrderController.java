package com.copious.training.api;

import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
     * API to get mock Order.
     *
     * @return Order
     * @throws IOException
     */
    @GetMapping(value = {"/order"})
    Order getMockOrder() throws IOException {
        return orderService.getMockOrder();
    }
}
