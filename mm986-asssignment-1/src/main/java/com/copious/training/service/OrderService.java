package com.copious.training.service;

import com.copious.training.api.errors.InvalidOrderException;
import com.copious.training.api.errors.ResourceNotFoundException;
import com.copious.training.constants.ExceptionCodeEnum;
import com.copious.training.dao.OrderMock;
import com.copious.training.dao.ProductMock;
import com.copious.training.domain.ImmutableOrder;
import com.copious.training.domain.Order;
import com.copious.training.domain.Sku;
import com.copious.training.repository.OrderRepository;
import com.copious.training.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

/**
 * @author Mahesh More.
 * <p>
 * Service class to operate on orders
 */
@Service
@Log4j2
public class OrderService {

    @Autowired
    private OrderMock orderMock;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductMock productMock;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Service method to get single Mock order.
     *
     * @return Order
     * @throws IOException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 100000, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Order getOrderById(String orderId) throws IOException, InterruptedException {
        return ServiceMappingUtil
                .mapToDomainOrder(orderRepository.findById(orderId))
                .orElseThrow(() ->
                        new ResourceNotFoundException(ExceptionCodeEnum.NOT_FOUND,
                                ExceptionCodeEnum.NOT_FOUND.getMessage(),
                                "OrderNotFound: Order is not available for OrderId: " + orderId));
    }

    /**
     * Service method to get all orders.
     *
     * @return Order
     * @throws IOException
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 100000, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public List<Order> getOrders() throws IOException, InterruptedException {
        return ServiceMappingUtil.mapToDomainOrders(orderRepository.findAll());
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
                            throw new InvalidOrderException(ExceptionCodeEnum.INVALID_ORDER,
                                    ExceptionCodeEnum.INVALID_ORDER.getMessage(),
                                    "Order Expired: Order date is in past for OrderId: " + order.getOrderId()
                            );
                        } else if (o.getShippingDate().isBefore(LocalDate.now())) {
                            throw new InvalidOrderException(ExceptionCodeEnum.INVALID_ORDER,
                                    ExceptionCodeEnum.INVALID_ORDER.getMessage(),
                                    "Order Expired: Shipping date is in past for OrderId: " + order.getOrderId()
                            );
                        }
                        log.info("Valid Order: Order {} placed successfully", order.getOrderId());
                        return o;
                    })
                    .findFirst();
        } catch (Exception e) {
            log.error("Exception during validation of incoming Order. {} : {} : {}",
                    e.getCause(),
                    e.getMessage(),
                    e.getStackTrace()
            );
            throw e;
        }
    }

    /**
     * Service method to build Order Sequentially.
     *
     * @return Order
     * @throws IOException
     */
    public Order loadMockOrderSequentially() throws IOException, InterruptedException {
        Order order = orderMock.getMockOrder();
        List<Sku> products = productMock.getMockProducts();
        return ImmutableOrder.builder()
                .from(order)
                .addAllLineItems(products)
                .build();
    }

    /**
     * Service method to build Order Parallelly.
     *
     * @return Order
     * @throws IOException
     */
    public Order loadMockOrderParallelly() throws IOException, ExecutionException, InterruptedException {

        CompletableFuture<Order> order = CompletableFuture.supplyAsync(() -> {
            try {
                return orderMock.getMockOrder();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        });

        CompletableFuture<List<Sku>> products = CompletableFuture.supplyAsync(() -> {
            try {
                return productMock.getMockProducts();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        Order order1 = ImmutableOrder
                .builder()
                .from(order.get())
                .addAllLineItems(products.get())
                .build();

        CompletableFuture.allOf(order, products).join();

        return order1;
    }
}
