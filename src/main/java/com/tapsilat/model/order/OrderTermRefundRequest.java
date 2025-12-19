package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class OrderTermRefundRequest {
    @JsonProperty("term_id")
    private String termId;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("term_payment_id")
    private String termPaymentId;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
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

    public String getTermPaymentId() {
        return termPaymentId;
    }

    public void setTermPaymentId(String termPaymentId) {
        this.termPaymentId = termPaymentId;
    }
}
