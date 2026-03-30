package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackURLDTO {
    @JsonProperty("callback_url")
    private String callbackUrl;

    @JsonProperty("fail_callback_url")
    private String failCallbackUrl;

    @JsonProperty("cancel_callback_url")
    private String cancelCallbackUrl;

    @JsonProperty("refund_callback_url")
    private String refundCallbackUrl;

    public CallbackURLDTO() {}

    public CallbackURLDTO(String callbackUrl, String failCallbackUrl) {
        this.callbackUrl = callbackUrl;
        this.failCallbackUrl = failCallbackUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getFailCallbackUrl() {
        return failCallbackUrl;
    }

    public void setFailCallbackUrl(String failCallbackUrl) {
        this.failCallbackUrl = failCallbackUrl;
    }

    public String getCancelCallbackUrl() {
        return cancelCallbackUrl;
    }

    public void setCancelCallbackUrl(String cancelCallbackUrl) {
        this.cancelCallbackUrl = cancelCallbackUrl;
    }

    public String getRefundCallbackUrl() {
        return refundCallbackUrl;
    }

    public void setRefundCallbackUrl(String refundCallbackUrl) {
        this.refundCallbackUrl = refundCallbackUrl;
    }
}
