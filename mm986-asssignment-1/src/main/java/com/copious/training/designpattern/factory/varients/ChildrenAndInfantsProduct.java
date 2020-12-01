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
 * Product Factory to get Products by CHILDREN_AND_INFANTS Category.
 */
@Component
public class ChildrenAndInfantsProduct implements Product {

    /**
     * Factory method to get products by CHILDREN_AND_INFANTS category.
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        return products
                .stream()
                .filter(sku -> ProductCategory.CHILDREN_AND_INFANTS.equals(sku.getCategory()))
                .collect(Collectors.toList());
    }
}
