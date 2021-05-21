package com.copious.training.designpattern.factory.varients;

import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.domain.Sku;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by CHILDREN_AND_INFANTS Category.
 */
@Component
public class ChildrenAndInfantsProduct implements Product {

    /**
     * Factory method to get products by CHILDREN_AND_INFANTS category.
     *
     * @return Product's
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        Predicate<Sku> skuPredicate = sku -> ProductCategoryEnum.CHILDREN_AND_INFANTS.equals(sku.getCategory());
        return products
                .stream()
                .filter(skuPredicate)
                .collect(Collectors.toList());
    }
}
