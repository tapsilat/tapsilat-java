package com.tapsilat.model.subscription;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionCreateResponse {
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    @JsonProperty("reference_id")
    private String referenceId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(String orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
