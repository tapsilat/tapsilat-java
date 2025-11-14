package com.tapsilat.order.enums;

/**
 * Supported billing types for Tapsilat orders.
 */
public enum BillingType {
    /**
     * Personal/individual billing
     */
    PERSONAL("PERSONAL"),
    
    /**
     * Business/corporate billing
     */
    BUSINESS("BUSINESS");
    
    private final String code;
    
    BillingType(String code) {
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
     * Get BillingType enum from string code.
     * @param code The billing type code
     * @return BillingType enum or null if not found
     */
    public static BillingType fromCode(String code) {
        if (code == null) {
            return null;
        }
        
        for (BillingType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
