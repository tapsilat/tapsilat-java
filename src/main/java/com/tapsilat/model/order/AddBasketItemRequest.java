package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tapsilat.model.common.BasketItem;

public class AddBasketItemRequest {
    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    @JsonProperty("basket_item")
    private BasketItem basketItem;

    public AddBasketItemRequest() {}

    public AddBasketItemRequest(String orderReferenceId, BasketItem basketItem) {
        this.orderReferenceId = orderReferenceId;
        this.basketItem = basketItem;
    }

    public String getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(String orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public BasketItem getBasketItem() {
        return basketItem;
    }

    public void setBasketItem(BasketItem basketItem) {
        this.basketItem = basketItem;
    }
}
