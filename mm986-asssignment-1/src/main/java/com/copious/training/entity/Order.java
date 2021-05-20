package com.copious.training.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Mahesh More.
 * <p>
 * Repository class for Cart table.
 */
@Entity
@Table(name = "CART")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String orderId;

    private String orderPhase;

    private LocalDate orderDate;

    private String emailAddress;

    @OneToOne
    @JoinColumn(name = "billingId")
    private CartAndBillingDetails cartAndBillingDetails;

    @OneToOne
    @JoinColumn(name = "shippingId")
    private ShippingDetails shippingDetails;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private Set<Item> lineItems;

    public Order() {
    }

    public Order(String orderId, String orderPhase, LocalDate orderDate, String emailAddress, CartAndBillingDetails cartAndBillingDetails, ShippingDetails shippingDetails, Set<Item> lineItems) {
        this.orderId = orderId;
        this.orderPhase = orderPhase;
        this.orderDate = orderDate;
        this.emailAddress = emailAddress;
        this.cartAndBillingDetails = cartAndBillingDetails;
        this.shippingDetails = shippingDetails;
        this.lineItems = lineItems;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderPhase() {
        return orderPhase;
    }

    public void setOrderPhase(String orderPhase) {
        this.orderPhase = orderPhase;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public CartAndBillingDetails getCartAndBillingDetails() {
        return cartAndBillingDetails;
    }

    public void setCartAndBillingDetails(CartAndBillingDetails cartAndBillingDetails) {
        this.cartAndBillingDetails = cartAndBillingDetails;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public Set<Item> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<Item> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderPhase='" + orderPhase + '\'' +
                ", orderDate=" + orderDate +
                ", emailAddress='" + emailAddress + '\'' +
                ", cartAndBillingDetails=" + cartAndBillingDetails +
                ", shippingDetails=" + shippingDetails +
                ", lineItems=" + lineItems +
                '}';
    }
}
