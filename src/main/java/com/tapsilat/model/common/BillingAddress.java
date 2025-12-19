package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents billing address information for an order.
 */
public class BillingAddress {
    
    @JsonProperty("address")
    private String address;
    
    @JsonProperty("city")
    private String city;
    
    @JsonProperty("contact_name")
    private String contactName;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("zip_code")
    private String zipCode;
    
    @JsonProperty("billing_type")
    private String billingType; // PERSONAL or BUSINESS
    
    @JsonProperty("contact_phone")
    private String contactPhone;
    
    @JsonProperty("district")
    private String district;
    
    @JsonProperty("vat_number")
    private String vatNumber;
    
    // Default constructor for Jackson
    public BillingAddress() {}
    
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
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getBillingType() {
        return billingType;
    }
    
    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getVatNumber() {
        return vatNumber;
    }
    
    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAddress that = (BillingAddress) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(country, that.country) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(billingType, that.billingType) &&
                Objects.equals(contactPhone, that.contactPhone) &&
                Objects.equals(district, that.district) &&
                Objects.equals(vatNumber, that.vatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, contactName, country, zipCode, 
                billingType, contactPhone, district, vatNumber);
    }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", contactName='" + contactName + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", billingType='" + billingType + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", district='" + district + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                '}';
    }
}
