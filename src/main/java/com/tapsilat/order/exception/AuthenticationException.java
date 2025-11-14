package com.tapsilat.order.exception;

/**
 * Exception thrown when API authentication fails.
 * Typically indicates missing or invalid bearer token.
 */
public class AuthenticationException extends TapsilatException {
    
    /**
     * Creates a new AuthenticationException with a message.
     * 
     * @param message The error message
     */
    public AuthenticationException(String message) {
        super(message);
    }
    
    /**
     * Creates a new AuthenticationException with a message and cause.
     * 
     * @param message The error message
     * @param cause The underlying cause
     */
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Creates a new AuthenticationException with status code.
     * 
     * @param message The error message
     * @param statusCode The HTTP status code
     */
    public AuthenticationException(String message, int statusCode) {
        super(message, statusCode);
    }
}
