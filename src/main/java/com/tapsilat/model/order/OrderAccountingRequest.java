package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class OrderAccountingRequest {
    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    public OrderAccountingRequest() {
    }

    public OrderAccountingRequest(String orderReferenceId) {
        this.orderReferenceId = Objects.requireNonNull(orderReferenceId, "Order reference ID cannot be null");
    }

    public String getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(String orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }
}
