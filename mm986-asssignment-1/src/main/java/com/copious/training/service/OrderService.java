package com.copious.training.service;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.dao.OrderRepository;
import com.copious.training.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Mahesh More.
 * <p>
 * Service class to operate on orders
 */
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    /**
     * Service method to get single Mock order.
     *
     * @return Order
     * @throws IOException
     */
    public Order getMockOrder() throws IOException {
        return orderRepository.getMockOrder();
    }
    
    /**
     * Service method to validate single order.
     *
     * @return Order
     * @throws IOException
     */
    public Optional<Order> validateOrder(Order order) {
        try {
            return Stream.of(order)
                    .map(o -> {
                        if (o.getOrderDate().isBefore(LocalDate.now())) {
                            throw new InvalidOrderException(HttpStatus.BAD_REQUEST
                                    , "Order Expired: Order date is in past for OrderId: " + order.getOrderId()
                            );
                        } else if (o.getShippingDate().isBefore(LocalDate.now())) {
                            throw new InvalidOrderException(HttpStatus.BAD_REQUEST
                                    , "Order Expired: Shipping date is in past for OrderId: " + order.getOrderId()
                            );
                        }
                        logger.info("Valid Order: Order {} placed successfully", order.getOrderId());
                        return o;
                    })
                    .findFirst();
        } catch (Exception e) {
            logger.error("Exception during validation of incoming Order. {} : {} : {}", e.getCause(), e.getMessage(), e.getStackTrace());
            throw e;
        }
    }
}
