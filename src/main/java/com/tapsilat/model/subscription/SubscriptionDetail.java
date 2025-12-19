package com.tapsilat.model.subscription;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SubscriptionDetail {
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("due_date")
    private String dueDate;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("is_active")
    private Boolean isActive;

    @JsonProperty("orders")
    private List<SubscriptionOrder> orders;

    @JsonProperty("payment_date")
    private Integer paymentDate;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("period")
    private Integer period;

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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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

    public List<SubscriptionOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<SubscriptionOrder> orders) {
        this.orders = orders;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
