package com.copious.training.domain;

import org.immutables.value.Value;

import java.math.BigDecimal;

/**
 * @author Mahesh More
 * Model to store SKU details.
 */
@Value.Immutable
public interface Sku {
    String getSku();

    String getName();

    String getCategory();

    String getOther();

    BigDecimal getUnitPrice();

    BigDecimal getSalePrice();

    int getQuantity();

    BigDecimal getTotalPrice();

    String getImageUrl();

    String getProductUrl();
}
