package com.copious.training.domain;

import com.copious.training.constants.ProductCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.math.BigDecimal;

/**
 * @author Mahesh More
 * <p>
 * Model to store SKU details.
 */
@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = ImmutableSku.Builder.class)
public interface Sku {
    String getSku();

    String getName();

    ProductCategory getCategory();

    String getOther();

    BigDecimal getUnitPrice();

    BigDecimal getSalePrice();

    int getQuantity();

    BigDecimal getTotalPrice();

    String getImageUrl();

    String getProductUrl();
}
