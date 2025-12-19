package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderPostAuthRequest {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("reference_id")
    private String referenceId;

    public OrderPostAuthRequest() {
    }

    public OrderPostAuthRequest(BigDecimal amount, String referenceId) {
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
}
