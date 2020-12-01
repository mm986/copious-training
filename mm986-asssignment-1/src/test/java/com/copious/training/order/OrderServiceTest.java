package com.copious.training.order;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.domain.ImmutableOrder;
import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    private static final String MOCK_ORDER_ID = "123ABC";

    @Test
    public void testGetMockOrder() throws IOException {
        assertEquals(MOCK_ORDER_ID, orderService.getMockOrder().getOrderId());
    }

    @Test
    public void testValidateOrder() throws IOException {
        assertEquals(MOCK_ORDER_ID, orderService
                .validateOrder(getValidOrder())
                .map(Order::getOrderId)
                .orElse("")
        );
    }

    @Test
    public void testOrderWithInvalidOrderDate() throws IOException {
        try {
            orderService.validateOrder(getOrderWithInvalidOrderDate());
        } catch (InvalidOrderException e) {
            logger.info("Test case testOrderWithInvalidOrderDate pass. Caught {} :: {}",
                    e.getClass(),
                    e.getMessage()
            );
        }
    }

    @Test
    public void testOrderWithInvalidShippingDate() throws IOException {
        try {
            orderService.validateOrder(getOrderWithInvalidShippingDate());
        } catch (InvalidOrderException e) {
            logger.info("Test case testOrderWithInvalidShippingDate pass. Caught {} :: {}",
                    e.getClass(),
                    e.getMessage()
            );
        }
    }

    private Order getValidOrder() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now())
                .shippingDate(LocalDate.now())
                .build();
    }

    private Order getOrderWithInvalidOrderDate() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now().minusDays(1))
                .shippingDate(LocalDate.now())
                .build();
    }

    private Order getOrderWithInvalidShippingDate() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now())
                .shippingDate(LocalDate.now().minusDays(1))
                .build();
    }
}
