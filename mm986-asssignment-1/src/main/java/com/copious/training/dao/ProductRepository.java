package com.copious.training.dao;

import com.copious.training.domain.Sku;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Product Repository to get mock Sku's.
 */
@Repository
public class ProductRepository {
    /**
     * Method to get single Mock products.
     *
     * @return Order
     * @throws IOException
     */
    public List<Sku> getMockProducts() throws IOException {

        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule()
                        .addSerializer(OffsetDateTimeSerializer.INSTANCE)
                        .addSerializer(LocalDateSerializer.INSTANCE)
                        .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE));

        return mapper.readValue(
                new ClassPathResource("mock/products.json").getFile(),
                new TypeReference<List<Sku>>() {
                }
        );
    }
}

