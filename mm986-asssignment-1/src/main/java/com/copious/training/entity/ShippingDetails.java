package com.copious.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mahesh More.
 * <p>
 * Entity class for ShippingDetails table.
 */
@Entity
public class ShippingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String shippingId;

    private LocalDate shippingDate;

    private String shippingDetails;

    private String shippingTrackingUrl;

    private BigDecimal shippingAmount;

    public ShippingDetails() {
    }

    public ShippingDetails(LocalDate shippingDate, String shippingDetails, String shippingTrackingUrl, BigDecimal shippingAmount) {
        this.shippingDate = shippingDate;
        this.shippingDetails = shippingDetails;
        this.shippingTrackingUrl = shippingTrackingUrl;
        this.shippingAmount = shippingAmount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getShippingTrackingUrl() {
        return shippingTrackingUrl;
    }

    public void setShippingTrackingUrl(String shippingTrackingUrl) {
        this.shippingTrackingUrl = shippingTrackingUrl;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    @Override
    public String toString() {
        return "ShippingDetails{" +
                "shippingDate=" + shippingDate +
                ", shippingDetails='" + shippingDetails + '\'' +
                ", shippingTrackingUrl='" + shippingTrackingUrl + '\'' +
                ", shippingAmount=" + shippingAmount +
                '}';
    }
}
