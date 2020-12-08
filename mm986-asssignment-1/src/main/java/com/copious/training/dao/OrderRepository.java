package com.copious.training.dao;

import com.copious.training.designpattern.ObjectMapperSingleton;
import com.copious.training.domain.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mahesh More
 * <p>
 * Order Repository to get mock orders. Demonstration of Try with resources.
 */
@Component
public class OrderRepository {
    /**
     * Method to get single Mock order.
     *
     * @return Order
     * @throws IOException
     */
    public Order getMockOrder() throws IOException {
        try (InputStream mockOrder = new ClassPathResource("mock/order.json").getInputStream()) {
            return ObjectMapperSingleton.getInstance().mapper.readValue(mockOrder, Order.class);
        }
    }
}
