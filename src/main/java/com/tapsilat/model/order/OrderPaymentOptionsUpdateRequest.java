package com.tapsilat.model.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPaymentOptionsUpdateRequest {

    @JsonProperty("payment_options")
    private List<String> paymentOptions;

    @JsonProperty("reference_id")
    private String referenceId;

    public OrderPaymentOptionsUpdateRequest() {
    }

    public OrderPaymentOptionsUpdateRequest(List<String> paymentOptions, String referenceId) {
        this.paymentOptions = paymentOptions;
        this.referenceId = referenceId;
    }

    public List<String> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }
}
