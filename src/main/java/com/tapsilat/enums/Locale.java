package com.tapsilat.enums;

/**
 * Supported locales for Tapsilat payment interface.
 */
public enum Locale {
    TR("tr"),
    EN("en");
    
    private final String code;
    
    Locale(String code) {
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
     * Get Locale enum from string code.
     * @param code The locale code
     * @return Locale enum or null if not found
     */
    public static Locale fromCode(String code) {
        if (code == null) {
            return null;
        }
        
        for (Locale locale : values()) {
            if (locale.code.equalsIgnoreCase(code)) {
                return locale;
            }
        }
        return null;
    }
} 