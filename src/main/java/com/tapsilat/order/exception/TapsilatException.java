package com.tapsilat.order.exception;

/**
 * Custom exception for Tapsilat API errors.
 */
public class TapsilatException extends Exception {
    
    private final int statusCode;
    
    /**
     * Constructor with message.
     * @param message The error message
     */
    public TapsilatException(String message) {
        super(message);
        this.statusCode = 0;
    }
    
    /**
     * Constructor with message and status code.
     * @param message The error message
     * @param statusCode The HTTP status code
     */
    public TapsilatException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    /**
     * Constructor with message and cause.
     * @param message The error message
     * @param cause The cause exception
     */
    public TapsilatException(String message, Throwable cause) {
        super(message, cause);
        this.statusCode = 0;
    }
    
    /**
     * Constructor with message, status code, and cause.
     * @param message The error message
     * @param statusCode The HTTP status code
     * @param cause The cause exception
     */
    public TapsilatException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }
    
    /**
     * Get the HTTP status code.
     * @return The status code, or 0 if not available
     */
    public int getStatusCode() {
        return statusCode;
    }
} 