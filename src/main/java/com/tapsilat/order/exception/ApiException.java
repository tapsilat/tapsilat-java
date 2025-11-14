package com.tapsilat.order.exception;

/**
 * Exception thrown when Tapsilat API returns an error response.
 * Contains HTTP status code and detailed error information from the API.
 */
public class ApiException extends TapsilatException {
    
    private final String errorCode;
    private final String responseBody;
    
    /**
     * Creates a new ApiException with message and status code.
     * 
     * @param message The error message
     * @param statusCode The HTTP status code
     */
    public ApiException(String message, int statusCode) {
        super(message, statusCode);
        this.errorCode = null;
        this.responseBody = null;
    }
    
    /**
     * Creates a new ApiException with full error details.
     * 
     * @param message The error message
     * @param statusCode The HTTP status code
     * @param errorCode The API error code
     * @param responseBody The full API response body
     */
    public ApiException(String message, int statusCode, String errorCode, String responseBody) {
        super(message, statusCode);
        this.errorCode = errorCode;
        this.responseBody = responseBody;
    }
    
    /**
     * Gets the API-specific error code if available.
     * 
     * @return The error code, or null if not available
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Gets the full API response body.
     * 
     * @return The response body, or null if not available
     */
    public String getResponseBody() {
        return responseBody;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (errorCode != null) {
            sb.append(", errorCode='").append(errorCode).append('\'');
        }
        if (responseBody != null && !responseBody.isEmpty()) {
            sb.append(", responseBody='").append(responseBody).append('\'');
        }
        return sb.toString();
    }
}
