package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SplitOrderItemPaymentRequest {

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_item_payment_id")
    private String orderItemPaymentId;

    public SplitOrderItemPaymentRequest() {
    }

    public SplitOrderItemPaymentRequest(Double amount, String orderId, String orderItemPaymentId) {
        this.amount = amount;
        this.orderId = orderId;
        this.orderItemPaymentId = orderItemPaymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemPaymentId() {
        return orderItemPaymentId;
    }

    public void setOrderItemPaymentId(String orderItemPaymentId) {
        this.orderItemPaymentId = orderItemPaymentId;
    }
}
