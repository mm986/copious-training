package com.copious.training.service;

import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.constants.ProductCategory;
import com.copious.training.dao.ProductRepository;
import com.copious.training.designpattern.factory.ProductFactory;
import com.copious.training.domain.Sku;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @author Mahesh More.
 * <p>
 * Service class to operate on Products and play with collections and streams
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductFactory productFactory;

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * Service method to get sorted list of available Products [ArrayList Implementation].
     *
     * @return Products
     * @throws IOException
     */
    public List<Sku> getProductListFromArrayList() throws IOException {

        List<Sku> products = new ArrayList<>();

        products.addAll(productRepository
                .getMockProducts()
                .stream()
                .sorted(Comparator.comparing(Sku::getSku)
                        .thenComparing(Sku::getName)
                        .thenComparing(Sku::getTotalPrice))
                .collect(Collectors.toList())
        );

        return products;
    }

    /**
     * Service method to get sorted list of available Products [LinkedList Implementation].
     *
     * @return Products
     * @throws IOException
     */
    public List<Sku> getProductListFromLinkedList() throws IOException {
        List<Sku> products = new LinkedList<>();

        products.addAll(productRepository
                .getMockProducts()
                .stream()
                .sorted(Comparator.comparing(Sku::getSku)
                        .thenComparing(Sku::getName)
                        .thenComparing(Sku::getTotalPrice))
                .collect(Collectors.toList())
        );

        return products;
    }

    /**
     * Service method to get expensive Product from list of available Products.
     *
     * @return Product
     * @throws IOException
     */
    public Sku getExpensiveProduct() throws IOException {
        return productRepository
                .getMockProducts()
                .stream()
                .max(Comparator.comparing(Sku::getTotalPrice))
                .orElseThrow(() -> new IllegalStateException("Product Not Available"));
    }

    /**
     * Service method to validate single product.
     *
     * @return Product
     */
    public Optional<Sku> validateProduct(Sku product) {
        try {
            return Stream.of(product)
                    .map(sku -> {

                        // Regex based validation on Product.
                        if (!Pattern
                                .compile("^[a-zA-Z0-9]+$")
                                .matcher(sku.getSku())
                                .matches()
                        ) {
                            throw new InvalidProductException(HttpStatus.BAD_REQUEST, "Invalid SKU: Sku "
                                    + sku.getSku()
                                    + " should match alphanumeric regular expression ^[a-zA-Z0-9]+$ "
                                    + "Special characters not allowed."
                            );
                        } else if (sku.getTotalPrice().compareTo(new BigDecimal(0)) == -1) {
                            throw new InvalidProductException(HttpStatus.BAD_REQUEST, "Invalid SKU: Sku "
                                    + sku.getSku()
                                    + " should have valid +ve price."
                            );
                        }
                        logger.info("Valid SKU: Sku " + sku.getSku() + " posted successfully");
                        return sku;
                    })
                    .findFirst();
        } catch (Exception e) {
            logger.error("Exception during validation of Product/SKU. {} : {} : {}",
                    e.getCause(),
                    e.getMessage(),
                    e.getStackTrace()
            );
            throw e;
        }
    }

    /**
     * Product method to get products by category. Demonstration of factory design pattern.
     *
     * @param category
     * @return Products
     * @throws IOException
     */
    public List<Sku> getProducts(ProductCategory category) throws IOException {
        return productFactory
                .getProductFactory(category)
                .getProducts(productRepository.getMockProducts());
    }
}

