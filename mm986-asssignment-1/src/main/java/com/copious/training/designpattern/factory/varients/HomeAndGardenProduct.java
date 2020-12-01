package com.copious.training.designpattern.factory.varients;

import com.copious.training.constants.ProductCategory;
import com.copious.training.domain.Sku;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by HOME_AND_GARDEN Category.
 */
@Component
public class HomeAndGardenProduct implements Product {

    /**
     * Factory method to get products by HOME_AND_GARDEN category.
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        return products
                .stream()
                .filter(sku -> ProductCategory.HOME_AND_GARDEN.equals(sku.getCategory()))
                .collect(Collectors.toList());
    }
}
