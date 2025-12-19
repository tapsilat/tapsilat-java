package com.tapsilat.model.subscription;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SubscriptionRedirectResponse {
    @JsonProperty("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
