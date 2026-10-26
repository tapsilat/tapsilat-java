package com.tapsilat.model.elements;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request body of {@code POST /api/v1/elements/sessions}.
 * <p>
 * {@code purpose} is required. Fields left {@code null} are not sent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsSessionCreateRequest {

    /** {@code tokenize} or {@code pay}; see {@link com.tapsilat.constants.ElementsConstants}. */
    @JsonProperty("purpose")
    private String purpose;

    /** Required for {@code pay}. */
    @JsonProperty("order_reference_id")
    private String orderReferenceId;

    /** Card owner of a {@code tokenize} session, scoped to the caller's organization. */
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_email")
    private String userEmail;

    /** Tokenize mode: 1 = 3D Secure, 2 = half secure, 3 = non-secure. */
    @JsonProperty("mode")
    private Integer mode;

    /** {@code pay}: allow the buyer to save the card. */
    @JsonProperty("save_card")
    private Boolean saveCard;

    public ElementsSessionCreateRequest() {
    }

    public ElementsSessionCreateRequest(String purpose) {
        this.purpose = purpose;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Boolean getSaveCard() {
        return saveCard;
    }

    public void setSaveCard(Boolean saveCard) {
        this.saveCard = saveCard;
    }
}
