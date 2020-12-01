package com.copious.training.designpattern.factory.varients;

import com.copious.training.domain.Sku;

import java.io.IOException;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * Product Interface for Product factroy.
 */
public interface Product {
    List<Sku> getProducts(List<Sku> products);
}
