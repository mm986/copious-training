package com.copious.training.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Mahesh More
 * <p>
 * This is model class to store the order details for specific cart order.
 */
@Value.Immutable
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = ImmutableOrder.Builder.class)
public interface Order {

    //Basic Order Details
    String getOrderId();

    String orderPhase();

    LocalDate getOrderDate();

    String getEmailAddress();

    // Order amount, tax, shipping, and promo discount
    BigDecimal getSubtotal();

    BigDecimal getTaxAmount();

    BigDecimal getShippingAmount();

    BigDecimal getDiscountAmount();

    BigDecimal getGrandTotal();

    String getCurrency();

    String getPromoApplied();

    // Cart and shipping details
    String getCartUrl();

    LocalDate getShippingDate();

    String getShippingDetails();

    String getShippingTrackingUrl();

    // Order Items/SKU's
    List<Sku> getLineItems();
}
