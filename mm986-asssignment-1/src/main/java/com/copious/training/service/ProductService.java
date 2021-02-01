package com.copious.training.service;

import com.copious.training.api.errors.InvalidProductException;
import com.copious.training.constants.ExceptionCodeEnum;
import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.dao.ProductMock;
import com.copious.training.designpattern.factory.ProductFactory;
import com.copious.training.domain.Sku;
import com.copious.training.entity.Item;
import com.copious.training.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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
@Log4j2
public class ProductService {

    @Autowired
    private ProductMock productMock;

    @Autowired
    private ProductFactory productFactory;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Service method to get sorted list of available Products [ArrayList Implementation].
     *
     * @return Products
     * @throws IOException
     */
    public List<Sku> getProductListFromArrayList() throws IOException, InterruptedException {

        List<Sku> products = new ArrayList<>();
        products.addAll(productMock
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
    public List<Sku> getProductListFromLinkedList() throws IOException, InterruptedException {
        List<Sku> products = new LinkedList<>();


        products.addAll(productMock
                .getMockProducts()
                .stream()
                .sorted(Comparator.comparing(Sku::getSku)
                        .thenComparing(Sku::getName)
                        .thenComparing(Sku::getTotalPrice))
                .collect(Collectors.toList())
        );
        products.get(0);
        return products;
    }

    /**
     * Service method to get expensive Product from list of available Products.
     *
     * @return Product
     * @throws IOException
     */
    public Sku getExpensiveProduct() throws IOException, InterruptedException {
        return productMock
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
                            throw new InvalidProductException(ExceptionCodeEnum.INVALID_PRODUCT,
                                    ExceptionCodeEnum.INVALID_PRODUCT.getMessage(),
                                    "Invalid SKU: Sku "
                                            + sku.getSku()
                                            + " should match alphanumeric regular expression ^[a-zA-Z0-9]+$ "
                                            + "Special characters not allowed."
                            );
                        } else if (sku.getTotalPrice().compareTo(new BigDecimal(0)) == -1) {
                            throw new InvalidProductException(ExceptionCodeEnum.INVALID_PRODUCT,
                                    ExceptionCodeEnum.INVALID_PRODUCT.getMessage(),
                                    "Invalid SKU: Sku " + sku.getSku() + " should have valid +ve price."
                            );
                        }
                        log.info("Valid SKU: Sku " + sku.getSku() + " posted successfully");
                        return sku;
                    })
                    .findFirst();
        } catch (Exception e) {
            log.error("Exception during validation of Product/SKU. {} : {} : {}",
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
    public List<Sku> getProducts(ProductCategoryEnum category) throws IOException, InterruptedException {
        return productFactory
                .getProductFactory(category)
                .getProducts(productMock.getMockProducts());
    }

    public List<Item> getItemsByCategory(String sku) {
        try {
            return (List<Item>) productRepository.getItemsBySku(sku);
        } catch (Exception e) {
            log.error("{}{}", e.getMessage(), e.getStackTrace());
            e.printStackTrace();
            return null;
        }
    }
}

