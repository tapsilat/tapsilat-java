package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class SetLimitUserRequest {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("limit")
    private BigDecimal limit;

    @JsonProperty("limit_id")
    private String limitId;

    public SetLimitUserRequest() {}

    public SetLimitUserRequest(String userId, BigDecimal limit) {
        this.userId = userId;
        this.limit = limit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public String getLimitId() {
        return limitId;
    }

    public void setLimitId(String limitId) {
        this.limitId = limitId;
    }
}
