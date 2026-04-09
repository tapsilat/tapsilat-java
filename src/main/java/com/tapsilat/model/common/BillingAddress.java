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

    @JsonProperty("citizenship")
    private String citizenship;

    @JsonProperty("title")
    private String title;

    @JsonProperty("tax_office")
    private String taxOffice;

    @JsonProperty("neighbourhood")
    private String neighbourhood;

    @JsonProperty("street1")
    private String street1;

    @JsonProperty("street2")
    private String street2;

    @JsonProperty("street3")
    private String street3;

    // Default constructor for Jackson
    public BillingAddress() {
    }

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

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BillingAddress that = (BillingAddress) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(country, that.country) &&
                Objects.equals(zipCode, that.zipCode) &&
                Objects.equals(billingType, that.billingType) &&
                Objects.equals(contactPhone, that.contactPhone) &&
                Objects.equals(district, that.district) &&
                Objects.equals(vatNumber, that.vatNumber) &&
                Objects.equals(citizenship, that.citizenship) &&
                Objects.equals(title, that.title) &&
                Objects.equals(taxOffice, that.taxOffice) &&
                Objects.equals(neighbourhood, that.neighbourhood) &&
                Objects.equals(street1, that.street1) &&
                Objects.equals(street2, that.street2) &&
                Objects.equals(street3, that.street3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, contactName, country, zipCode,
                billingType, contactPhone, district, vatNumber, citizenship, title,
                taxOffice, neighbourhood, street1, street2, street3);
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
                ", citizenship='" + citizenship + '\'' +
                ", title='" + title + '\'' +
                ", taxOffice='" + taxOffice + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", street3='" + street3 + '\'' +
                '}';
    }
}
