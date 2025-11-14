package com.tapsilat.order.validation;

import com.tapsilat.order.constants.TapsilatConstants;
import com.tapsilat.order.model.Buyer;
import com.tapsilat.order.model.OrderRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Validates OrderRequest objects before sending to Tapsilat API.
 * Provides detailed validation error messages with field context.
 */
public final class OrderRequestValidator {
    
    private static final BigDecimal MINIMUM_AMOUNT = BigDecimal.ZERO;
    
    private OrderRequestValidator() {
        // Utility class - prevent instantiation
    }
    
    /**
     * Validates an OrderRequest and returns list of validation errors.
     * Empty list indicates valid request.
     * 
     * @param request The order request to validate
     * @return List of validation error messages, empty if valid
     * @throws ValidationException if request is null
     */
    public static List<String> validate(OrderRequest request) {
        if (request == null) {
            throw new ValidationException("OrderRequest cannot be null");
        }
        
        List<String> errors = new ArrayList<>();
        
        validateAmount(request.getAmount(), errors);
        validateCurrency(request.getCurrency(), errors);
        validateLocale(request.getLocale(), errors);
        validateBuyer(request.getBuyer(), errors);
        validateDescription(request.getDescription(), errors);
        validateCallbackUrl(request.getCallbackUrl(), errors);
        validateConversationId(request.getConversationId(), errors);
        
        return errors;
    }
    
    /**
     * Validates an OrderRequest and throws ValidationException if invalid.
     * 
     * @param request The order request to validate
     * @throws ValidationException if validation fails
     */
    public static void validateOrThrow(OrderRequest request) {
        List<String> errors = validate(request);
        if (!errors.isEmpty()) {
            throw new ValidationException("Order request validation failed", errors);
        }
    }
    
    private static void validateAmount(BigDecimal amount, List<String> errors) {
        if (amount == null) {
            errors.add("Amount is required and cannot be null");
        } else if (amount.compareTo(MINIMUM_AMOUNT) <= 0) {
            errors.add("Amount must be greater than zero, got: " + amount);
        }
    }
    
    private static void validateCurrency(String currency, List<String> errors) {
        if (currency == null || currency.trim().isEmpty()) {
            errors.add("Currency is required and cannot be null or empty");
        }
    }
    
    private static void validateLocale(String locale, List<String> errors) {
        if (locale == null || locale.trim().isEmpty()) {
            errors.add("Locale is required and cannot be null or empty");
        }
    }
    
    private static void validateBuyer(Buyer buyer, List<String> errors) {
        if (buyer == null) {
            errors.add("Buyer information is required and cannot be null");
            return;
        }
        
        if (buyer.getName() == null || buyer.getName().trim().isEmpty()) {
            errors.add("Buyer name is required and cannot be null or empty");
        }
        
        if (buyer.getSurname() == null || buyer.getSurname().trim().isEmpty()) {
            errors.add("Buyer surname is required and cannot be null or empty");
        }
        
        if (buyer.getEmail() == null || buyer.getEmail().trim().isEmpty()) {
            errors.add("Buyer email is required and cannot be null or empty");
        } else if (!isValidEmail(buyer.getEmail())) {
            errors.add("Buyer email format is invalid: " + buyer.getEmail());
        }
    }
    
    private static void validateDescription(String description, List<String> errors) {
        if (description != null && description.length() > TapsilatConstants.MAX_DESCRIPTION_LENGTH) {
            errors.add("Description exceeds maximum length of " + TapsilatConstants.MAX_DESCRIPTION_LENGTH 
                + " characters, got: " + description.length());
        }
    }
    
    private static void validateCallbackUrl(String callbackUrl, List<String> errors) {
        if (callbackUrl != null && !callbackUrl.trim().isEmpty()) {
            if (!isValidUrl(callbackUrl)) {
                errors.add("Callback URL format is invalid: " + callbackUrl);
            }
        }
    }
    
    private static void validateConversationId(String conversationId, List<String> errors) {
        if (conversationId != null && conversationId.length() > TapsilatConstants.MAX_CONVERSATION_ID_LENGTH) {
            errors.add("Conversation ID exceeds maximum length of " + TapsilatConstants.MAX_CONVERSATION_ID_LENGTH 
                + " characters, got: " + conversationId.length());
        }
    }
    
    private static boolean isValidEmail(String email) {
        // Basic email validation - can be enhanced with more sophisticated regex
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    
    private static boolean isValidUrl(String url) {
        // Basic URL validation - checks for http/https scheme
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }
}
