package com.tapsilat.exception;

/**
 * Exception thrown when Tapsilat API returns an error response.
 * Contains HTTP status code and detailed error information from the API.
 * <p>
 * When the error body is {@code {"error": {"code": "...", "message": "...", "retryable": true}}},
 * as on the Elements routes, {@link #getErrorCode()}, {@link #getErrorMessage()} and
 * {@link #isRetryable()} carry those fields.
 */
public class ApiException extends TapsilatException {
    
    private final String errorCode;
    private final String errorMessage;
    private final boolean retryable;
    private final String responseBody;
    
    /**
     * Creates a new ApiException with a message only.
     *
     * @param message The error message
     */
    protected ApiException(String message) {
        super(message);
        this.errorCode = null;
        this.errorMessage = null;
        this.retryable = false;
        this.responseBody = null;
    }

    /**
     * Creates a new ApiException with a message and cause.
     *
     * @param message The error message
     * @param cause The underlying cause
     */
    protected ApiException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.errorMessage = null;
        this.retryable = false;
        this.responseBody = null;
    }

    /**
     * Creates a new ApiException with message and status code.
     * 
     * @param message The error message
     * @param statusCode The HTTP status code
     */
    public ApiException(String message, int statusCode) {
        super(message, statusCode);
        this.errorCode = null;
        this.errorMessage = null;
        this.retryable = false;
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
        this(message, statusCode, errorCode, null, false, responseBody);
    }

    /**
     * Creates a new ApiException with full error details, including the API's own message and
     * whether the request can be retried.
     *
     * @param message The error message
     * @param statusCode The HTTP status code
     * @param errorCode The API error code
     * @param errorMessage The API error message
     * @param retryable Whether the API says the request can be retried
     * @param responseBody The full API response body
     */
    public ApiException(String message, int statusCode, String errorCode, String errorMessage,
            boolean retryable, String responseBody) {
        super(message, statusCode);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.retryable = retryable;
        this.responseBody = responseBody;
    }
    
    /**
     * Gets the API-specific error code if available, for example {@code session_not_found}.
     * 
     * @return The error code, or null if not available
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the API's own error message if available, without the SDK's prefix.
     *
     * @return The API error message, or null if not available
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Whether the API marked the error as retryable, for example a 503 {@code unavailable}.
     *
     * @return true when the error object says the request can be retried
     */
    public boolean isRetryable() {
        return retryable;
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
        if (retryable) {
            sb.append(", retryable=true");
        }
        if (responseBody != null && !responseBody.isEmpty()) {
            sb.append(", responseBody='").append(responseBody).append('\'');
        }
        return sb.toString();
    }
}
