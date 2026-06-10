package com.tapsilat.model.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

/**
 * @deprecated Use amount/cycle/period fields in SubscriptionCreateRequest.
 */
@Deprecated
public class SubscriptionPriceOption {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("price")
    private BigDecimal price;

    public SubscriptionPriceOption() {
    }

    public SubscriptionPriceOption(Integer count, BigDecimal price) {
        this.count = count;
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
