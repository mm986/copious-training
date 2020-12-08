package com.copious.training.dao;

import com.copious.training.designpattern.ObjectMapperSingleton;
import com.copious.training.domain.Sku;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Product Repository to get mock Sku's.
 */
@Repository
public class ProductRepository {
    /**
     * Method to get single Mock products. Demonstration of Try with resources.
     *
     * @return Order
     * @throws IOException
     */
    public List<Sku> getMockProducts() throws IOException {
        try (InputStream mockProduct = new ClassPathResource("mock/products.json").getInputStream()) {
            return ObjectMapperSingleton.getInstance().mapper.readValue(mockProduct,
                    new TypeReference<List<Sku>>() {
                    }
            );
        }
    }
}

