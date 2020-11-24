package com.copious.training.dao;

import com.copious.training.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Mahesh More
 * Order Repository to get mock orders.
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

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule()
                        .addSerializer(OffsetDateTimeSerializer.INSTANCE)
                        .addSerializer(LocalDateSerializer.INSTANCE)
                        .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE));

        return mapper.readValue(new ClassPathResource("mock/order.json").getFile(), Order.class);
    }
}
