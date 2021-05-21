package com.copious.training.entity;

import com.copious.training.constants.ProductCategoryEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mahesh More.
 * <p>
 * Repository class for Item table.
 */
@Entity
@Table(name = "ITEM")
@NamedNativeQuery(name = "Item.getItemBySku", query = "Select * from Item i where i.sku = :sku", resultClass = Item.class)
//@NamedQuery(name = "Item.getItemBySku", query = "FROM Item WHERE sku = :sku")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sku;

    private String name;

    private ProductCategoryEnum category;

    private String other;

    private BigDecimal unitPrice;

    private BigDecimal salePrice;

    private int quantity;

    private BigDecimal totalPrice;

    private String imageUrl;

    private String productUrl;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    public Item() {
    }

    public Item(String sku, String name, ProductCategoryEnum category, String other, BigDecimal unitPrice, BigDecimal salePrice, int quantity, BigDecimal totalPrice, String imageUrl, String productUrl, Order order) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.other = other;
        this.unitPrice = unitPrice;
        this.salePrice = salePrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
        this.productUrl = productUrl;
        this.order = order;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryEnum category) {
        this.category = category;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", other='" + other + '\'' +
                ", unitPrice=" + unitPrice +
                ", salePrice=" + salePrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", productUrl='" + productUrl + '\'' +
                ", order=" + order +
                '}';
    }
}
