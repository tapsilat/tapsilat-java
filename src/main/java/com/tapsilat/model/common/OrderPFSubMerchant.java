package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPFSubMerchant {
    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_iso_code")
    private String countryIsoCode;

    @JsonProperty("id")
    private String id;

    @JsonProperty("mcc")
    private String mcc;

    @JsonProperty("name")
    private String name;

    @JsonProperty("org_id")
    private String orgId;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("submerchant_nin")
    private String submerchantNin;

    @JsonProperty("submerchant_url")
    private String submerchantUrl;

    @JsonProperty("terminal_no")
    private String terminalNo;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSubmerchantNin() {
        return submerchantNin;
    }

    public void setSubmerchantNin(String submerchantNin) {
        this.submerchantNin = submerchantNin;
    }

    public String getSubmerchantUrl() {
        return submerchantUrl;
    }

    public void setSubmerchantUrl(String submerchantUrl) {
        this.submerchantUrl = submerchantUrl;
    }

    public String getTerminalNo() {
        return terminalNo;
    }

    public void setTerminalNo(String terminalNo) {
        this.terminalNo = terminalNo;
    }
}
