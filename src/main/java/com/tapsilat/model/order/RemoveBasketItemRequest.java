package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoveBasketItemRequest {
    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    @JsonProperty("basket_item_id")
    private String basketItemId;

    public RemoveBasketItemRequest() {}

    public RemoveBasketItemRequest(String orderReferenceId, String basketItemId) {
        this.orderReferenceId = orderReferenceId;
        this.basketItemId = basketItemId;
    }

    public String getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(String orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public String getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(String basketItemId) {
        this.basketItemId = basketItemId;
    }
}
