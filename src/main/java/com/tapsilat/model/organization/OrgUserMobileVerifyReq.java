package com.tapsilat.model.organization;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgUserMobileVerifyReq {
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("code")
    private String code;

    public OrgUserMobileVerifyReq() {}

    public OrgUserMobileVerifyReq(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
