package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents the response from Tapsilat order creation API.
 * Based on actual API response: {"order_id":"...","reference_id":"..."}
 */
public class OrderResponse {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("checkout_url")
    private String checkoutUrl;

    // Default constructor
    public OrderResponse() {
    }

    /**
     * Constructor with all fields.
     */
    public OrderResponse(String orderId, String referenceId) {
        this.orderId = orderId;
        this.referenceId = referenceId;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getCheckoutUrl() {
        return checkoutUrl;
    }

    public void setCheckoutUrl(String checkoutUrl) {
        this.checkoutUrl = checkoutUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderResponse that = (OrderResponse) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(referenceId, that.referenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, referenceId);
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "orderId='" + orderId + '\'' +
                ", referenceId='" + referenceId + '\'' +
                '}';
    }
}