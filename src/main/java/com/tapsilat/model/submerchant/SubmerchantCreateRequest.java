package com.tapsilat.model.submerchant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmerchantCreateRequest {
    @JsonProperty("locale")
    private String locale;
    
    @JsonProperty("conversation_id")
    private String conversationId;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("gsm_number")
    private String gsmNumber;
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("iban")
    private String iban;
    
    @JsonProperty("tax_office")
    private String taxOffice;
    
    @JsonProperty("legal_company_title")
    private String legalCompanyTitle;
    
    @JsonProperty("currency_id")
    private String currencyId;
    
    @JsonProperty("sub_merchant_external_id")
    private String subMerchantExternalId;
    
    @JsonProperty("identity_number")
    private String identityNumber;
    
    @JsonProperty("sub_merchant_type")
    private String subMerchantType;
    
    @JsonProperty("tax_number")
    private String taxNumber;
    
    @JsonProperty("sub_merchant_key")
    private String subMerchantKey;
    
    @JsonProperty("organization_id")
    private String organizationId;
    
    @JsonProperty("status")
    private String status;
    
    @JsonProperty("system_time")
    private Integer systemTime;
    
    @JsonProperty("contact_name")
    private String contactName;
    
    @JsonProperty("contact_surname")
    private String contactSurname;

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public String getConversationId() { return conversationId; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGsmNumber() { return gsmNumber; }
    public void setGsmNumber(String gsmNumber) { this.gsmNumber = gsmNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public String getTaxOffice() { return taxOffice; }
    public void setTaxOffice(String taxOffice) { this.taxOffice = taxOffice; }

    public String getLegalCompanyTitle() { return legalCompanyTitle; }
    public void setLegalCompanyTitle(String legalCompanyTitle) { this.legalCompanyTitle = legalCompanyTitle; }

    public String getCurrencyId() { return currencyId; }
    public void setCurrencyId(String currencyId) { this.currencyId = currencyId; }

    public String getSubMerchantExternalId() { return subMerchantExternalId; }
    public void setSubMerchantExternalId(String subMerchantExternalId) { this.subMerchantExternalId = subMerchantExternalId; }

    public String getIdentityNumber() { return identityNumber; }
    public void setIdentityNumber(String identityNumber) { this.identityNumber = identityNumber; }

    public String getSubMerchantType() { return subMerchantType; }
    public void setSubMerchantType(String subMerchantType) { this.subMerchantType = subMerchantType; }

    public String getTaxNumber() { return taxNumber; }
    public void setTaxNumber(String taxNumber) { this.taxNumber = taxNumber; }

    public String getSubMerchantKey() { return subMerchantKey; }
    public void setSubMerchantKey(String subMerchantKey) { this.subMerchantKey = subMerchantKey; }

    public String getOrganizationId() { return organizationId; }
    public void setOrganizationId(String organizationId) { this.organizationId = organizationId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getSystemTime() { return systemTime; }
    public void setSystemTime(Integer systemTime) { this.systemTime = systemTime; }

    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getContactSurname() { return contactSurname; }
    public void setContactSurname(String contactSurname) { this.contactSurname = contactSurname; }
}
