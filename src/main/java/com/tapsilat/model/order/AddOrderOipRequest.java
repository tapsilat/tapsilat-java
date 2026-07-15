package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddOrderOipRequest {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("url")
    private String url;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
