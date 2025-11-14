package com.tapsilat.order.enums;

/**
 * Supported item types for basket items in Tapsilat orders.
 */
public enum ItemType {
    /**
     * Physical product that requires shipping
     */
    PHYSICAL("PHYSICAL"),
    
    /**
     * Digital product delivered electronically
     */
    DIGITAL("DIGITAL"),
    
    /**
     * Service item
     */
    SERVICE("SERVICE");
    
    private final String code;
    
    ItemType(String code) {
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
     * Get ItemType enum from string code.
     * @param code The item type code
     * @return ItemType enum or null if not found
     */
    public static ItemType fromCode(String code) {
        if (code == null) {
            return null;
        }
        
        for (ItemType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        return null;
    }
}
