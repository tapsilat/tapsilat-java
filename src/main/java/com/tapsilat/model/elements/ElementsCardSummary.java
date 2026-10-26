package com.tapsilat.model.elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Card used on an Elements session. Holds no card number or CVV.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ElementsCardSummary {

    @JsonProperty("id")
    private String id;

    /** For example {@code visa}; see {@link com.tapsilat.constants.ElementsConstants}. */
    @JsonProperty("brand")
    private String brand;

    @JsonProperty("last4")
    private String last4;

    @JsonProperty("exp_month")
    private Integer expMonth;

    @JsonProperty("exp_year")
    private Integer expYear;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    @Override
    public String toString() {
        return "ElementsCardSummary{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", last4='" + last4 + '\'' +
                ", expMonth=" + expMonth +
                ", expYear=" + expYear +
                '}';
    }
}
