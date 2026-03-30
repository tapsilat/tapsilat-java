package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetUserLimitRequest {
    @JsonProperty("user_id")
    private String userId;

    public GetUserLimitRequest() {}

    public GetUserLimitRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
