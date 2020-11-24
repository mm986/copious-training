package com.copious.training.service;

import com.copious.training.dao.OrderRepository;
import com.copious.training.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Mahesh More.
 * <p>
 * Service class to operate on orders
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    /**
     * Service method call to get single Mock order.
     *
     * @return Order
     * @throws IOException
     */
    public Order getMockOrder() throws IOException {
        return orderRepository.getMockOrder();
    }
}
