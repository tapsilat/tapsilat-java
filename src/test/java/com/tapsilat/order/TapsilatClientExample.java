package com.tapsilat.order;

import com.tapsilat.order.builder.OrderRequestBuilder;
import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;
import com.tapsilat.order.exception.TapsilatException;
import com.tapsilat.order.model.Buyer;
import com.tapsilat.order.model.Metadata;
import com.tapsilat.order.model.OrderRequest;
import com.tapsilat.order.model.OrderResponse;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Example usage of the Tapsilat Java SDK.
 */
public class TapsilatClientExample {
    
    public static void main(String[] args) {
        // Example 1: Basic usage with configuration
        basicExample();
        
        // Example 2: Using builder pattern
        builderExample();
        
        // Example 3: Using enums
        enumExample();
        
        // Example 4: With metadata
        metadataExample();
    }
    
    /**
     * Basic example showing direct object creation.
     */
    private static void basicExample() {
        System.out.println("=== Basic Example ===");
        
        try {
            // Create configuration with bearer token
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");
            
            // Create client
            TapsilatClient client = new TapsilatClient(config);
            
            // Create buyer
            Buyer buyer = new Buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111");
            
            // Create order request
            OrderRequest orderRequest = new OrderRequest();
            orderRequest.setAmount(new BigDecimal("150.75"));
            orderRequest.setCurrency("TRY");
            orderRequest.setLocale("tr");
            orderRequest.setBuyer(buyer);
            orderRequest.setDescription("Premium Subscription - Annual Plan");
            orderRequest.setCallbackUrl("https://your-website.com/payment-complete");
            orderRequest.setConversationId("order-" + System.currentTimeMillis());
            
            // Create order
            OrderResponse response = client.createOrder(orderRequest);
            
            System.out.println("\nOrder created successfully!");
            System.out.println("Order ID: " + response.getOrderId());
            System.out.println("Reference ID: " + response.getReferenceId());
            
            // Close client
            client.close();
            
        } catch (TapsilatException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }
    
    /**
     * Example using the builder pattern.
     */
    private static void builderExample() {
        System.out.println("\n=== Builder Example ===");
        
        try {
            // Create client with bearer token configuration
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");

            TapsilatClient client = new TapsilatClient(config);
            
            // Create order request using builder
            OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                    .amount(150.75)
                    .currency("TRY")
                    .locale("tr")
                    .buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111")
                    .description("Premium Subscription - Annual Plan")
                    .callbackUrl("https://your-website.com/payment-complete")
                    .conversationId("order-" + System.currentTimeMillis())
                    .build();
            
            // Create order
            OrderResponse response = client.createOrder(orderRequest);
            
            System.out.println("\nOrder created successfully!");
            System.out.println("Order ID: " + response.getOrderId());
            System.out.println("Reference ID: " + response.getReferenceId());
            
            // Close client
            client.close();
            
        } catch (TapsilatException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }
    
    /**
     * Example using enums for currency and locale.
     */
    private static void enumExample() {
        System.out.println("\n=== Enum Example ===");
        
        try {
            // Create client with bearer token configuration
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");

            TapsilatClient client = new TapsilatClient(config);
            
            // Create order request using enums
            OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                    .amount(new BigDecimal("99.99"))
                    .currency(Currency.USD)
                    .locale(Locale.EN)
                    .buyer("Jane", "Smith", "jane.smith@example.com")
                    .description("Monthly Subscription")
                    .callbackUrl("https://your-website.com/payment-complete")
                    .conversationId("order-" + System.currentTimeMillis())
                    .build();
            
            // Create order
            OrderResponse response = client.createOrder(orderRequest);
            
            System.out.println("\nOrder created successfully!");
            System.out.println("Order ID: " + response.getOrderId());
            System.out.println("Reference ID: " + response.getReferenceId());
            
            // Close client
            client.close();
            
        } catch (TapsilatException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }
    
    /**
     * Example with metadata.
     */
    private static void metadataExample() {
        System.out.println("\n=== Metadata Example ===");
        
        try {
            // Create client with bearer token configuration
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");

            TapsilatClient client = new TapsilatClient(config);
            
            // Create metadata
            Metadata productMetadata = new Metadata("productId", "PREMIUM-12M");
            Metadata customerMetadata = new Metadata("customerType", "new");
            Metadata campaignMetadata = new Metadata("campaign", "summer2025");
            
            // Create order request with metadata
            OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                    .amount(150.75)
                    .currency(Currency.TRY)
                    .locale(Locale.TR)
                    .buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111")
                    .description("Premium Subscription - Annual Plan")
                    .callbackUrl("https://your-website.com/payment-complete")
                    .conversationId("order-" + System.currentTimeMillis())
                    .metadata(productMetadata)
                    .metadata(customerMetadata)
                    .metadata(campaignMetadata)
                    .build();
            
            // Alternative way to add metadata
            // orderRequest.setMetadata(Arrays.asList(productMetadata, customerMetadata, campaignMetadata));
            
            // Create order
            OrderResponse response = client.createOrder(orderRequest);
            
            System.out.println("\nOrder created successfully!");
            System.out.println("Order ID: " + response.getOrderId());
            System.out.println("Reference ID: " + response.getReferenceId());
            
            // Close client
            client.close();
            
        } catch (TapsilatException e) {
            System.err.println("Error creating order: " + e.getMessage());
        }
    }
} 