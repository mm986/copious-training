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

/**
 * @author Mahesh More.
 * <p>
 * Test class to test various operations on Order.
 */
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    Logger logger = LoggerFactory.getLogger(OrderServiceTest.class);

    private static final String MOCK_ORDER_ID = "123ABC";

    /**
     * Intended to test GetMockOrder
     *
     * @throws IOException
     */
    @Test
    public void testGetMockOrder() throws IOException {
        assertEquals(MOCK_ORDER_ID, orderService.getMockOrder().getOrderId());
    }

    /**
     * Intended to test ValidateOrder successfully
     *
     * @throws IOException
     */
    @Test
    public void testValidateOrder() throws IOException {
        assertEquals(MOCK_ORDER_ID, orderService
                .validateOrder(getValidOrder())
                .map(Order::getOrderId)
                .orElse("")
        );
    }

    /**
     * Intended to test OrderWithInvalidOrderDate.
     *
     * @throws IOException
     */
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

    /**
     * Intended to test OrderWithInvalidShippingDate.
     *
     * @throws IOException
     */
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

    /**
     * Mocking ValidOrder
     *
     * @return Order
     * @throws IOException
     */
    private Order getValidOrder() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now())
                .shippingDate(LocalDate.now())
                .build();
    }

    /**
     * Mocking OrderWithInvalidOrderDate
     *
     * @return Order
     * @throws IOException
     */
    private Order getOrderWithInvalidOrderDate() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now().minusDays(1))
                .shippingDate(LocalDate.now())
                .build();
    }

    /**
     * Mocking OrderWithInvalidShippingDate
     *
     * @return Order
     * @throws IOException
     */
    private Order getOrderWithInvalidShippingDate() throws IOException {
        return ImmutableOrder
                .builder()
                .from(orderService.getMockOrder())
                .orderDate(LocalDate.now())
                .shippingDate(LocalDate.now().minusDays(1))
                .build();
    }
}
