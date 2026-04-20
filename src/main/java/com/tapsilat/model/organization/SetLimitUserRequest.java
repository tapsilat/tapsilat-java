package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class SetLimitUserRequest {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("limit_id")
    private String limitId;

    public SetLimitUserRequest() {}

    public SetLimitUserRequest(String userId, String limitId) {
        this.userId = userId;
        this.limitId = limitId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getLimitId() {
        return limitId;
    }

    public void setLimitId(String limitId) {
        this.limitId = limitId;
    }
}
