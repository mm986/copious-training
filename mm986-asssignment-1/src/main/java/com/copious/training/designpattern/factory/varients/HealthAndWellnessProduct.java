package com.copious.training.designpattern.factory.varients;

import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.domain.Sku;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by HEALTH_AND_WELLNESS Category.
 */
@Component
public class HealthAndWellnessProduct implements Product {

    /**
     * Factory method to get products by HEALTH_AND_WELLNESS category.
     *
     * @return Product's
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        return products
                .stream()
                .filter(sku -> ProductCategoryEnum.HEALTH_AND_WELLNESS.equals(sku.getCategory()))
                .collect(Collectors.toList());
    }
}
