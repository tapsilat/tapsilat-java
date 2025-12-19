package com.tapsilat.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exception thrown when order request validation fails.
 * Contains detailed list of validation errors.
 * This is an unchecked exception for ease of use in validation flows.
 */
public class ValidationException extends RuntimeException {
    
    private final List<String> validationErrors;
    
    /**
     * Creates a new ValidationException with a single error message.
     * 
     * @param message The error message
     */
    public ValidationException(String message) {
        super(message);
        this.validationErrors = Collections.singletonList(message);
    }
    
    /**
     * Creates a new ValidationException with multiple validation errors.
     * 
     * @param message General error message
     * @param validationErrors List of specific validation errors
     */
    public ValidationException(String message, List<String> validationErrors) {
        super(buildMessage(message, validationErrors));
        this.validationErrors = new ArrayList<>(validationErrors);
    }
    
    /**
     * Gets the list of validation error messages.
     * 
     * @return Unmodifiable list of validation errors
     */
    public List<String> getValidationErrors() {
        return Collections.unmodifiableList(validationErrors);
    }
    
    /**
     * Checks if there are multiple validation errors.
     * 
     * @return true if multiple errors exist
     */
    public boolean hasMultipleErrors() {
        return validationErrors.size() > 1;
    }
    
    private static String buildMessage(String message, List<String> errors) {
        if (errors.isEmpty()) {
            return message;
        }
        
        StringBuilder sb = new StringBuilder(message);
        sb.append(": ");
        
        if (errors.size() == 1) {
            sb.append(errors.get(0));
        } else {
            sb.append(errors.size()).append(" validation errors:\n");
            for (int i = 0; i < errors.size(); i++) {
                sb.append("  ").append(i + 1).append(". ").append(errors.get(i));
                if (i < errors.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        
        return sb.toString();
    }
}
