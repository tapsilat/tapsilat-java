package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents shipping address information for an order.
 */
public class ShippingAddress {
    
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
    
    // Default constructor for Jackson
    public ShippingAddress() {}
    
    /**
     * Constructor with all fields.
     */
    public ShippingAddress(String address, String city, String contactName, String country, String zipCode) {
        this.address = address;
        this.city = city;
        this.contactName = contactName;
        this.country = country;
        this.zipCode = zipCode;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingAddress that = (ShippingAddress) o;
        return Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(contactName, that.contactName) &&
                Objects.equals(country, that.country) &&
                Objects.equals(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, contactName, country, zipCode);
    }

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", contactName='" + contactName + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
