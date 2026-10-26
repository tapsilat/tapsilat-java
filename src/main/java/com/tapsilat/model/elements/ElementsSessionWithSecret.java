package com.tapsilat.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A newly created Elements session, as returned by {@code POST /api/v1/elements/sessions}.
 * <p>
 * Hand {@link #getClientSecret()} to the app that opens the card sheet; it is returned only once.
 * Do not log or store it. {@link #toString()} prints it as {@code [redacted]}, while JSON
 * serialization keeps it so that it can still be sent to the app.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsSessionWithSecret extends ElementsSession {

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("elements_base_url")
    private String elementsBaseUrl;

    /**
     * The session's client secret. Pass it to your app only; do not log or store it.
     */
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getElementsBaseUrl() {
        return elementsBaseUrl;
    }

    public void setElementsBaseUrl(String elementsBaseUrl) {
        this.elementsBaseUrl = elementsBaseUrl;
    }

    @Override
    public String toString() {
        return "ElementsSessionWithSecret{" + fieldsToString() +
                ", clientSecret=" + (clientSecret != null ? "[redacted]" : "null") +
                ", elementsBaseUrl='" + elementsBaseUrl + '\'' +
                '}';
    }
}
