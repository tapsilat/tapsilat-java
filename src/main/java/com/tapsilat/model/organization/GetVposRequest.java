package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetVposRequest {
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("currency_id")
    private String currencyId;

    public GetVposRequest() {}

    public GetVposRequest(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
