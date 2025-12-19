package com.tapsilat.model.order;
import com.tapsilat.model.common.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class OrderManualCallbackRequest {
    @JsonProperty("reference_id")
    private String referenceId;

    @JsonProperty("conversation_id")
    private String conversationId;

    public OrderManualCallbackRequest() {
    }

    public OrderManualCallbackRequest(String referenceId) {
        this.referenceId = Objects.requireNonNull(referenceId, "Reference ID cannot be null");
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }
}
