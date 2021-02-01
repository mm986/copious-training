package com.copious.training.order;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.dao.OrderMock;
import com.copious.training.domain.ImmutableOrder;
import com.copious.training.domain.Order;
import com.copious.training.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
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
@Log4j2
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderMock orderMock;

    private static final String MOCK_ORDER_ID = "123ABC";

    /**
     * Intended to test GetMockOrder
     *
     * @throws IOException
     */
    @Ignore
    @Test
    public void testGetMockOrder() throws IOException, InterruptedException {
        assertEquals(MOCK_ORDER_ID, orderService.getOrderById("").getOrderId());
    }

    /**
     * Intended to test ValidateOrder successfully
     *
     * @throws IOException
     */
    @Test
    public void testValidateOrder() throws IOException, InterruptedException {
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
        } catch (InvalidOrderException | InterruptedException e) {
            log.info("Test case testOrderWithInvalidOrderDate pass. Caught {} :: {}",
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
        } catch (InvalidOrderException | InterruptedException e) {
            log.info("Test case testOrderWithInvalidShippingDate pass. Caught {} :: {}",
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
    private Order getValidOrder() throws IOException, InterruptedException {
        return ImmutableOrder
                .builder()
                .from(orderMock.getMockOrder())
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
    private Order getOrderWithInvalidOrderDate() throws IOException, InterruptedException {
        return ImmutableOrder
                .builder()
                .from(orderMock.getMockOrder())
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
    private Order getOrderWithInvalidShippingDate() throws IOException, InterruptedException {
        return ImmutableOrder
                .builder()
                .from(orderMock.getMockOrder())
                .orderDate(LocalDate.now())
                .shippingDate(LocalDate.now().minusDays(1))
                .build();
    }
}
