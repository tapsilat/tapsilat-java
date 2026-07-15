package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgUserTokenRequest {
    @JsonProperty("email")
    private String email;

    @JsonProperty("expire")
    private Integer expire;
    
    @JsonProperty("invalidate_old_tokens")
    private Boolean invalidateOldTokens;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getExpire() { return expire; }
    public void setExpire(Integer expire) { this.expire = expire; }

    public Boolean getInvalidateOldTokens() { return invalidateOldTokens; }
    public void setInvalidateOldTokens(Boolean invalidateOldTokens) { this.invalidateOldTokens = invalidateOldTokens; }
}
