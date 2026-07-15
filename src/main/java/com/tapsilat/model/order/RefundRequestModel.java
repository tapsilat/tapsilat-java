package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefundRequestModel {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("amount")
    private Double amount;
    
    @JsonProperty("reason")
    private String reason;
    
    @JsonProperty("description")
    private String description;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
