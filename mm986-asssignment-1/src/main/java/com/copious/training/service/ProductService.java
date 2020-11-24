package com.copious.training.service;

import com.copious.training.dao.ProductRepository;
import com.copious.training.domain.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Mahesh More.
 * <p>
 * Service class to operate on Products and play with collections and streams
 */
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * Service method to get sorted list of available Products [ArrayList Implementation].
     *
     * @return Sku's
     * @throws IOException
     */
    public List<Sku> getProductListFromArrayList() throws IOException {

        List<Sku> products=new ArrayList<>();

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
     * @return Sku's
     * @throws IOException
     */
    public List<Sku> getProductListFromLinkedList() throws IOException {
        List<Sku> products=new LinkedList<>();

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
     * @return Sku
     * @throws IOException
     */
    public Sku getExpensiveProduct() throws IOException {
        return productRepository
                .getMockProducts()
                .stream()
                .max(Comparator.comparing(Sku::getTotalPrice))
                .orElseThrow(() -> new IllegalStateException("Product Not Available"));
    }
}
