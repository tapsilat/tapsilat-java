package com.tapsilat.model.common;

import com.tapsilat.enums.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents buyer/customer information for payment orders.
 * Contains required fields (name, surname, email) and optional fields (phone,
 * identityNumber).
 */
public class Buyer {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("identityNumber")
    private String identityNumber;

    @JsonProperty("birth_date")
    private String birthDate;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;

    @JsonProperty("gsm_number")
    private String gsmNumber;

    @JsonProperty("id")
    private String id;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("last_login_date")
    private String lastLoginDate;

    @JsonProperty("registration_address")
    private String registrationAddress;

    @JsonProperty("registration_date")
    private String registrationDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("zip_code")
    private String zipCode;

    // Default constructor for Jackson deserialization
    public Buyer() {
    }

    /**
     * Constructor with required fields.
     * 
     * @param name    Buyer's first name (required)
     * @param surname Buyer's last name (required)
     * @param email   Buyer's email address (required)
     * @throws NullPointerException if any required parameter is null
     */
    public Buyer(String name, String surname, String email) {
        this.name = Objects.requireNonNull(name, "Buyer name cannot be null");
        this.surname = Objects.requireNonNull(surname, "Buyer surname cannot be null");
        this.email = Objects.requireNonNull(email, "Buyer email cannot be null");
    }

    /**
     * Constructor with all fields.
     * 
     * @param name           Buyer's first name (required)
     * @param surname        Buyer's last name (required)
     * @param email          Buyer's email address (required)
     * @param phone          Buyer's phone number (optional)
     * @param identityNumber Buyer's identity number (optional)
     * @throws NullPointerException if any required parameter is null
     */
    public Buyer(String name, String surname, String email, String phone, String identityNumber) {
        this.name = Objects.requireNonNull(name, "Buyer name cannot be null");
        this.surname = Objects.requireNonNull(surname, "Buyer surname cannot be null");
        this.email = Objects.requireNonNull(email, "Buyer email cannot be null");
        this.phone = phone;
        this.identityNumber = identityNumber;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Buyer name cannot be null");
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = Objects.requireNonNull(surname, "Buyer surname cannot be null");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "Buyer email cannot be null");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Buyer buyer = (Buyer) o;
        return Objects.equals(name, buyer.name) &&
                Objects.equals(surname, buyer.surname) &&
                Objects.equals(email, buyer.email) &&
                Objects.equals(phone, buyer.phone) &&
                Objects.equals(identityNumber, buyer.identityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email, phone, identityNumber);
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", identityNumber='" + identityNumber + '\'' +
                '}';
    }
}