package com.tapsilat.model.common;
import com.tapsilat.enums.*;


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

    @JsonProperty("commission_amount")
    private Double commissionAmount;

    @JsonProperty("coupon")
    private String coupon;

    @JsonProperty("paid_amount")
    private Double paidAmount;

    @JsonProperty("payer")
    private BasketItemPayer payer;

    @JsonProperty("quantity_float")
    private Double quantityFloat;

    @JsonProperty("sub_merchant_key")
    private String subMerchantKey;

    @JsonProperty("sub_merchant_price")
    private String subMerchantPrice;

    @JsonProperty("mcc")
    private String mcc;

    // Default constructor for Jackson
    public BasketItem() {
    }

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

    public Double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public BasketItemPayer getPayer() {
        return payer;
    }

    public void setPayer(BasketItemPayer payer) {
        this.payer = payer;
    }

    public Double getQuantityFloat() {
        return quantityFloat;
    }

    public void setQuantityFloat(Double quantityFloat) {
        this.quantityFloat = quantityFloat;
    }

    public String getSubMerchantKey() {
        return subMerchantKey;
    }

    public void setSubMerchantKey(String subMerchantKey) {
        this.subMerchantKey = subMerchantKey;
    }

    public String getSubMerchantPrice() {
        return subMerchantPrice;
    }

    public void setSubMerchantPrice(String subMerchantPrice) {
        this.subMerchantPrice = subMerchantPrice;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
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
                Objects.equals(quantityUnit, that.quantityUnit) &&
                Objects.equals(commissionAmount, that.commissionAmount) &&
                Objects.equals(coupon, that.coupon) &&
                Objects.equals(paidAmount, that.paidAmount) &&
                Objects.equals(payer, that.payer) &&
                Objects.equals(quantityFloat, that.quantityFloat) &&
                Objects.equals(subMerchantKey, that.subMerchantKey) &&
                Objects.equals(subMerchantPrice, that.subMerchantPrice) &&
                Objects.equals(mcc, that.mcc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category1, category2, price, quantity,
                itemType, couponDiscount, data, quantityUnit, commissionAmount,
                coupon, paidAmount, payer, quantityFloat, subMerchantKey,
                subMerchantPrice, mcc);
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
                ", commissionAmount=" + commissionAmount +
                ", coupon='" + coupon + '\'' +
                ", paidAmount=" + paidAmount +
                ", payer=" + payer +
                ", quantityFloat=" + quantityFloat +
                ", subMerchantKey='" + subMerchantKey + '\'' +
                ", subMerchantPrice='" + subMerchantPrice + '\'' +
                ", mcc='" + mcc + '\'' +
                '}';
    }
}
