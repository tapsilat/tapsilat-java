package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;

public class RefundOrderRequest {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("order_item_id")
    private String orderItemId;

    @JsonProperty("order_item_payment_id")
    private String orderItemPaymentId;

    public RefundOrderRequest() {
    }

    public RefundOrderRequest(BigDecimal amount, String referenceId) {
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.referenceId = Objects.requireNonNull(referenceId, "Reference ID cannot be null");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderItemPaymentId() {
        return orderItemPaymentId;
    }

    public void setOrderItemPaymentId(String orderItemPaymentId) {
        this.orderItemPaymentId = orderItemPaymentId;
    }
}
