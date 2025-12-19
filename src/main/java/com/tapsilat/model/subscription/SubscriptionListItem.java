package com.tapsilat.model.subscription;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionListItem {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("payment_date")
    private Integer paymentDate;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("period")
    private Integer period;

    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("title")
    private String title;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
