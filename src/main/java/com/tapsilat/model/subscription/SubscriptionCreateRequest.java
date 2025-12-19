package com.tapsilat.model.subscription;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class SubscriptionCreateRequest {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("billing")
    private SubscriptionBilling billing;

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("cycle")
    private Integer cycle;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("failure_url")
    private String failureUrl;

    @JsonProperty("payment_date")
    private Integer paymentDate;

    @JsonProperty("period")
    private Integer period;

    @JsonProperty("success_url")
    private String successUrl;

    @JsonProperty("title")
    private String title;

    @JsonProperty("user")
    private SubscriptionUser user;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SubscriptionBilling getBilling() {
        return billing;
    }

    public void setBilling(SubscriptionBilling billing) {
        this.billing = billing;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public Integer getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Integer paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubscriptionUser getUser() {
        return user;
    }

    public void setUser(SubscriptionUser user) {
        this.user = user;
    }
}
