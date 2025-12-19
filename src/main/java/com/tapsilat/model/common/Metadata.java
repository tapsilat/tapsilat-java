package com.tapsilat.model.common;
import com.tapsilat.enums.*;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Represents metadata key-value pairs for order reference.
 * Both key and value are required fields.
 */
public class Metadata {
    
    @JsonProperty("key")
    private String key;
    
    @JsonProperty("value")
    private String value;
    
    // Default constructor for Jackson deserialization
    public Metadata() {}
    
    /**
     * Constructor with key and value.
     * @param key The metadata key (required)
     * @param value The metadata value (required)
     * @throws NullPointerException if key or value is null
     */
    public Metadata(String key, String value) {
        this.key = Objects.requireNonNull(key, "Metadata key cannot be null");
        this.value = Objects.requireNonNull(value, "Metadata value cannot be null");
    }
    
    // Getters and Setters
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = Objects.requireNonNull(key, "Metadata key cannot be null");
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = Objects.requireNonNull(value, "Metadata value cannot be null");
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Metadata metadata = (Metadata) o;
        return Objects.equals(key, metadata.key) &&
                Objects.equals(value, metadata.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}