package com.tapsilat.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents a basket item in an order.
 */
public class BasketItem {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("category1")
    private String category1;
    
    @JsonProperty("category2")
    private String category2;
    
    @JsonProperty("price")
    private Double price;
    
    @JsonProperty("quantity")
    private Integer quantity;
    
    @JsonProperty("item_type")
    private String itemType;
    
    @JsonProperty("coupon_discount")
    private Double couponDiscount;
    
    @JsonProperty("data")
    private String data;
    
    @JsonProperty("quantity_unit")
    private String quantityUnit;
    
    // Default constructor for Jackson
    public BasketItem() {}
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCategory1() {
        return category1;
    }
    
    public void setCategory1(String category1) {
        this.category1 = category1;
    }
    
    public String getCategory2() {
        return category2;
    }
    
    public void setCategory2(String category2) {
        this.category2 = category2;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getItemType() {
        return itemType;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    
    public Double getCouponDiscount() {
        return couponDiscount;
    }
    
    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }
    
    public String getQuantityUnit() {
        return quantityUnit;
    }
    
    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(category1, that.category1) &&
                Objects.equals(category2, that.category2) &&
                Objects.equals(price, that.price) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(itemType, that.itemType) &&
                Objects.equals(couponDiscount, that.couponDiscount) &&
                Objects.equals(data, that.data) &&
                Objects.equals(quantityUnit, that.quantityUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category1, category2, price, quantity, 
                itemType, couponDiscount, data, quantityUnit);
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category1='" + category1 + '\'' +
                ", category2='" + category2 + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", itemType='" + itemType + '\'' +
                ", couponDiscount=" + couponDiscount +
                ", data='" + data + '\'' +
                ", quantityUnit='" + quantityUnit + '\'' +
                '}';
    }
}
