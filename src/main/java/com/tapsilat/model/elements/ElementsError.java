package com.tapsilat.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Last error recorded on an Elements session, for example a declined card.
 * This is session data, not an exception.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsError {

    /** Stable error code, for example {@code card_declined}. */
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("retryable")
    private Boolean retryable;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getRetryable() {
        return retryable;
    }

    public void setRetryable(Boolean retryable) {
        this.retryable = retryable;
    }

    @Override
    public String toString() {
        return "ElementsError{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", retryable=" + retryable +
                '}';
    }
}
