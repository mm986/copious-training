package com.copious.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author Mahesh More.
 * <p>
 * Entity class for BillingDetails table.
 */
@Entity
public class CartAndBillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String billingId;

    private BigDecimal subtotal;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private BigDecimal grandTotal;

    private String currency;

    private String promoApplied;

    private String cartUrl;

    public CartAndBillingDetails() {
    }

    public CartAndBillingDetails(BigDecimal subtotal, BigDecimal taxAmount, BigDecimal discountAmount, BigDecimal grandTotal, String currency, String promoApplied, String cartUrl) {
        this.subtotal = subtotal;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.grandTotal = grandTotal;
        this.currency = currency;
        this.promoApplied = promoApplied;
        this.cartUrl = cartUrl;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPromoApplied() {
        return promoApplied;
    }

    public void setPromoApplied(String promoApplied) {
        this.promoApplied = promoApplied;
    }

    public String getCartUrl() {
        return cartUrl;
    }

    public void setCartUrl(String cartUrl) {
        this.cartUrl = cartUrl;
    }

    @Override
    public String toString() {
        return "CartAndBillingDetails{" +
                "subtotal=" + subtotal +
                ", taxAmount=" + taxAmount +
                ", discountAmount=" + discountAmount +
                ", grandTotal=" + grandTotal +
                ", currency='" + currency + '\'' +
                ", promoApplied='" + promoApplied + '\'' +
                ", cartUrl='" + cartUrl + '\'' +
                '}';
    }
}
