package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SubOrganization {
    @JsonProperty("acquirer")
    private String acquirer;

    @JsonProperty("address")
    private String address;

    @JsonProperty("contact_first_name")
    private String contactFirstName;

    @JsonProperty("contact_last_name")
    private String contactLastName;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("email")
    private String email;

    @JsonProperty("gsm_number")
    private String gsmNumber;

    @JsonProperty("iban")
    private String iban;

    @JsonProperty("identity_number")
    private String identityNumber;

    @JsonProperty("legal_company_title")
    private String legalCompanyTitle;

    @JsonProperty("organization_name")
    private String organizationName;

    @JsonProperty("sub_merchant_external_id")
    private String subMerchantExternalId;

    @JsonProperty("sub_merchant_key")
    private String subMerchantKey;

    @JsonProperty("sub_merchant_type")
    private String subMerchantType;

    @JsonProperty("tax_number")
    private String taxNumber;

    @JsonProperty("tax_office")
    private String taxOffice;

    public String getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(String acquirer) {
        this.acquirer = acquirer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getLegalCompanyTitle() {
        return legalCompanyTitle;
    }

    public void setLegalCompanyTitle(String legalCompanyTitle) {
        this.legalCompanyTitle = legalCompanyTitle;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSubMerchantExternalId() {
        return subMerchantExternalId;
    }

    public void setSubMerchantExternalId(String subMerchantExternalId) {
        this.subMerchantExternalId = subMerchantExternalId;
    }

    public String getSubMerchantKey() {
        return subMerchantKey;
    }

    public void setSubMerchantKey(String subMerchantKey) {
        this.subMerchantKey = subMerchantKey;
    }

    public String getSubMerchantType() {
        return subMerchantType;
    }

    public void setSubMerchantType(String subMerchantType) {
        this.subMerchantType = subMerchantType;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }
}
