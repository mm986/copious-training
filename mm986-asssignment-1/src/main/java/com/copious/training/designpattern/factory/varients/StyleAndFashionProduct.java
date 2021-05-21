package com.copious.training.designpattern.factory.varients;

import com.copious.training.constants.ProductCategoryEnum;
import com.copious.training.domain.Sku;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mahesh More
 * <p>
 * Product Factory to get Products by STYLE_AND_FASHION Category.
 */
@Service
public class StyleAndFashionProduct implements Product {

    /**
     * Factory method to get products by STYLE_AND_FASHION category.
     *
     * @return Product's
     * @throws IOException
     */
    @Override
    public List<Sku> getProducts(List<Sku> products) {
        return products
                .stream()
                .filter(sku -> ProductCategoryEnum.STYLE_AND_FASHION.equals(sku.getCategory()))
                .collect(Collectors.toList());
    }
}
