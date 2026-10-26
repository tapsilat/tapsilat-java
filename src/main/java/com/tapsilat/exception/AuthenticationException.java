package com.tapsilat.exception;

/**
 * Exception thrown when API authentication fails.
 * Typically indicates missing or invalid bearer token (HTTP 401), or a request
 * the token is not allowed to make (HTTP 403).
 * <p>
 * It is an {@link ApiException}, so the error code, message and retryable flag
 * of the response are available on it as well.
 */
public class AuthenticationException extends ApiException {
    
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

    /**
     * Creates a new AuthenticationException with full error details.
     *
     * @param message The error message
     * @param statusCode The HTTP status code
     * @param errorCode The API error code
     * @param errorMessage The API error message
     * @param retryable Whether the API says the request can be retried
     * @param responseBody The full API response body
     */
    public AuthenticationException(String message, int statusCode, String errorCode, String errorMessage,
            boolean retryable, String responseBody) {
        super(message, statusCode, errorCode, errorMessage, retryable, responseBody);
    }
}
