package com.copious.training.service;

import com.copious.training.domain.ImmutableOrder;
import com.copious.training.domain.ImmutableSku;
import com.copious.training.domain.Order;
import com.copious.training.domain.Sku;
import com.copious.training.entity.Item;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Mahesh More.
 * <p>
 * Utility class to Map Entities to domain Objects.
 */
public class ServiceMappingUtil {

    /**
     * Utility method to map Orders entity to Domain object for Orders
     *
     * @param orders
     * @return
     */
    public static List<Order> mapToDomainOrders(Iterable<com.copious.training.entity.Order> orders) {
        return StreamSupport
                .stream(orders.spliterator(), false)
                .map(order -> mapToDomainOrder(Optional.ofNullable(order)).orElse(null))
                .collect(Collectors.toList());
    }

    /**
     * Utility method to map Order entity to Domain object for Order
     *
     * @param orderEntity
     * @return
     */
    public static Optional<Order> mapToDomainOrder(Optional<com.copious.training.entity.Order> orderEntity) {
        return orderEntity
                .map(order -> ImmutableOrder
                        .builder()
                        .orderId(order.getOrderId())
                        .addAllLineItems(mapToDomainProducts(order.getLineItems()))
                        .emailAddress(order.getEmailAddress())
                        .orderDate(order.getOrderDate())
                        .orderPhase(order.getOrderPhase())
                        .cartUrl(order.getCartAndBillingDetails().getCartUrl())
                        .currency(order.getCartAndBillingDetails().getCurrency())
                        .discountAmount(order.getCartAndBillingDetails().getDiscountAmount())
                        .grandTotal(order.getCartAndBillingDetails().getGrandTotal())
                        .promoApplied(order.getCartAndBillingDetails().getPromoApplied())
                        .subtotal(order.getCartAndBillingDetails().getSubtotal())
                        .taxAmount(order.getCartAndBillingDetails().getTaxAmount())
                        .shippingAmount(order.getShippingDetails().getShippingAmount())
                        .shippingDate(order.getShippingDetails().getShippingDate())
                        .shippingDetails(order.getShippingDetails().getShippingDetails())
                        .shippingTrackingUrl(order.getShippingDetails().getShippingTrackingUrl())
                        .build()
                );
    }

    /**
     * Utility method to map Item entity to Domain object to represent Products
     *
     * @param itemSet
     * @return
     */
    public static List<Sku> mapToDomainProducts(Set<Item> itemSet) {
        return itemSet
                .stream()
                .map(item -> ImmutableSku
                        .builder()
                        .sku(item.getSku())
                        .imageUrl(item.getImageUrl())
                        .unitPrice(item.getUnitPrice())
                        .salePrice(item.getSalePrice())
                        .quantity(item.getQuantity())
                        .productUrl(item.getProductUrl())
                        .other(item.getOther())
                        .totalPrice(item.getTotalPrice())
                        .category(item.getCategory())
                        .name(item.getName())
                        .build())
                .collect(Collectors.toList());
    }
}
