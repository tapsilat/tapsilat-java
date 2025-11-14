package com.tapsilat.order.constants;

/**
 * Constants used throughout the Tapsilat SDK.
 */
public final class TapsilatConstants {
    
    private TapsilatConstants() {
        // Utility class - prevent instantiation
    }
    
    // HTTP Headers
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String MEDIA_TYPE_JSON = "application/json";
    
    // API Endpoints
    public static final String ENDPOINT_CREATE_ORDER = "/api/v1/order/create";
    
    // Default Configuration
    public static final String DEFAULT_BASE_URL = "https://panel.tapsilat.com";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000; // 30 seconds
    public static final int DEFAULT_READ_TIMEOUT = 60000; // 60 seconds
    
    // Validation Limits
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    public static final int MAX_CONVERSATION_ID_LENGTH = 255;
    
    // HTTP Status Codes
    public static final int HTTP_OK = 200;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_INTERNAL_ERROR = 500;
}
