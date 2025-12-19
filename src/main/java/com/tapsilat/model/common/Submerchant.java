package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class Submerchant {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("merchant_reference_id")
    private String merchantReferenceId;

    @JsonProperty("order_basket_item_id")
    private String orderBasketItemId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMerchantReferenceId() {
        return merchantReferenceId;
    }

    public void setMerchantReferenceId(String merchantReferenceId) {
        this.merchantReferenceId = merchantReferenceId;
    }

    public String getOrderBasketItemId() {
        return orderBasketItemId;
    }

    public void setOrderBasketItemId(String orderBasketItemId) {
        this.orderBasketItemId = orderBasketItemId;
    }
}
