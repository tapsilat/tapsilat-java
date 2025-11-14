package com.tapsilat.order.enums;

/**
 * Supported currencies for Tapsilat payments.
 */
public enum Currency {
    TRY("TRY"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP");
    
    private final String code;
    
    Currency(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
    
    @Override
    public String toString() {
        return code;
    }
    
    /**
     * Get Currency enum from string code.
     * @param code The currency code
     * @return Currency enum or null if not found
     */
    public static Currency fromCode(String code) {
        if (code == null) {
            return null;
        }
        
        for (Currency currency : values()) {
            if (currency.code.equalsIgnoreCase(code)) {
                return currency;
            }
        }
        return null;
    }
} 