package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetVposRequest {
    @JsonProperty("currency_id")
    private String currencyId;

    public GetVposRequest() {}

    public GetVposRequest(String currencyId) {
        this.currencyId = currencyId;
    }


    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }
}
