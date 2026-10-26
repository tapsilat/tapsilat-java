package com.tapsilat.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Authoritative state of a Tapsilat Elements session, as returned by
 * {@code GET /api/v1/elements/sessions/{id}} and {@code POST /api/v1/elements/sessions/{id}/cancel}.
 * <p>
 * {@code purpose}, {@code status} and the card {@code brand} are plain strings so that values added
 * to the API later still decode; {@link com.tapsilat.constants.ElementsConstants} lists the known ones.
 * Timestamps are RFC 3339 strings. Any field may be {@code null} when the API omits it.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsSession {

    @JsonProperty("id")
    private String id;

    @JsonProperty("purpose")
    private String purpose;

    @JsonProperty("status")
    private String status;

    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("attempts")
    private Integer attempts;

    @JsonProperty("max_attempts")
    private Integer maxAttempts;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("completed_at")
    private String completedAt;

    @JsonProperty("card")
    private ElementsCardSummary card;

    @JsonProperty("last_error")
    private ElementsError lastError;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderReferenceId() {
        return orderReferenceId;
    }

    public void setOrderReferenceId(String orderReferenceId) {
        this.orderReferenceId = orderReferenceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }

    public ElementsCardSummary getCard() {
        return card;
    }

    public void setCard(ElementsCardSummary card) {
        this.card = card;
    }

    public ElementsError getLastError() {
        return lastError;
    }

    public void setLastError(ElementsError lastError) {
        this.lastError = lastError;
    }

    @Override
    public String toString() {
        return "ElementsSession{" + fieldsToString() + '}';
    }

    /**
     * The session fields as printed by {@link #toString()}, for subclasses that add their own.
     */
    protected String fieldsToString() {
        return "id='" + id + '\'' +
                ", purpose='" + purpose + '\'' +
                ", status='" + status + '\'' +
                ", orderReferenceId='" + orderReferenceId + '\'' +
                ", userId='" + userId + '\'' +
                ", attempts=" + attempts +
                ", maxAttempts=" + maxAttempts +
                ", createdAt='" + createdAt + '\'' +
                ", expiresAt='" + expiresAt + '\'' +
                ", completedAt='" + completedAt + '\'' +
                ", card=" + card +
                ", lastError=" + lastError;
    }
}
