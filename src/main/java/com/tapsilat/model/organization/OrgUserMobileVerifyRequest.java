package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgUserMobileVerifyRequest {
    @JsonProperty("user_id")
    private String userId;

    public OrgUserMobileVerifyRequest() {}

    public OrgUserMobileVerifyRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
