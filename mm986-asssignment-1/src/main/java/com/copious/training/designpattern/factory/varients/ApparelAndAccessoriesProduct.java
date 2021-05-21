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
 * Product Factory to get Products by APPAREL_AND_ACCESSORIES Category.
 */
@Component
public class ApparelAndAccessoriesProduct implements Product {

    /**
     * Factory method to get products by APPAREL_AND_ACCESSORIES category.
     *
     * @return Product's
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        return products
                .stream()
                .filter(sku -> ProductCategoryEnum.APPAREL_AND_ACCESSORIES.equals(sku.getCategory()))
                .collect(Collectors.toList());
    }
}
