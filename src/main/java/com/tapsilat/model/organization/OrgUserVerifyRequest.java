package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgUserVerifyRequest {
    @JsonProperty("user_id")
    private String userId;

    public OrgUserVerifyRequest() {}

    public OrgUserVerifyRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
