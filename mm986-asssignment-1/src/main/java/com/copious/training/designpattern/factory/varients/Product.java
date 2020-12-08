package com.copious.training.designpattern.factory.varients;

import com.copious.training.domain.Sku;

import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Product Interface for Product factroy.
 */
public interface Product {
    /**
     * Abstract method to get Product's.
     *
     * @param products
     * @return
     */
    List<Sku> getProducts(List<Sku> products);
}
