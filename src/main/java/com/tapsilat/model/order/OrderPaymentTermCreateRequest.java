package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class OrderPaymentTermCreateRequest {
    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("term_reference_id")
    private String termReferenceId;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("term_sequence")
    private Integer termSequence;

    @JsonProperty("required")
    private Boolean required;

    @JsonProperty("status")
    private String status;

    @JsonProperty("data")
    private String data;

    @JsonProperty("paid_date")
    private String paidDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTermReferenceId() {
        return termReferenceId;
    }

    public void setTermReferenceId(String termReferenceId) {
        this.termReferenceId = termReferenceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getTermSequence() {
        return termSequence;
    }

    public void setTermSequence(Integer termSequence) {
        this.termSequence = termSequence;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(String paidDate) {
        this.paidDate = paidDate;
    }
}
