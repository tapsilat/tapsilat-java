package com.tapsilat.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a consent required for an order.
 */
public class OrderConsent {
    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    public OrderConsent() {
    }

    public OrderConsent(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "OrderConsent{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
