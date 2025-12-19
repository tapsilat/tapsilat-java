package com.tapsilat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.builder.OrderRequestBuilder;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.enums.Currency;
import com.tapsilat.enums.Locale;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.common.Buyer;
import com.tapsilat.model.common.Metadata;
import com.tapsilat.model.order.OrderRequest;
import com.tapsilat.model.order.OrderResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TapsilatClient.
 */
class TapsilatClientTest {
    
    private TapsilatClient client;
    
    @BeforeEach
    void setUp() {
        // Note: In real tests, you would use mock HTTP client or test credentials
        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://panel.tapsilat.dev");
        config.setBearerToken("your-bearer-token");
        client = new TapsilatClient(config);
    }
    
    @Test
    void testOrderRequestBuilder() {
        // Test builder pattern
        OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                .amount(150.75)
                .currency(Currency.TRY)
                .locale(Locale.TR)
                .buyer("John", "Doe", "john.doe@example.com")
                .description("Test Order")
                .callbackUrl("https://example.com/callback")
                .conversationId("test-order-123")
                .metadata("testKey", "testValue")
                .build();
        
        // Verify required fields
        assertEquals(new BigDecimal("150.75"), orderRequest.getAmount());
        assertEquals("TRY", orderRequest.getCurrency());
        assertEquals("tr", orderRequest.getLocale());
        assertNotNull(orderRequest.getBuyer());
        assertEquals("John", orderRequest.getBuyer().getName());
        assertEquals("Doe", orderRequest.getBuyer().getSurname());
        assertEquals("john.doe@example.com", orderRequest.getBuyer().getEmail());
        
        // Verify optional fields
        assertEquals("Test Order", orderRequest.getDescription());
        assertEquals("https://example.com/callback", orderRequest.getCallbackUrl());
        assertEquals("test-order-123", orderRequest.getConversationId());
        assertNotNull(orderRequest.getMetadata());
        assertEquals(1, orderRequest.getMetadata().size());
        assertEquals("testKey", orderRequest.getMetadata().get(0).getKey());
        assertEquals("testValue", orderRequest.getMetadata().get(0).getValue());
    }
    
    @Test
    void testBuyerConstructor() {
        // Test buyer with required fields only
        Buyer buyer1 = new Buyer("John", "Doe", "john.doe@example.com");
        assertEquals("John", buyer1.getName());
        assertEquals("Doe", buyer1.getSurname());
        assertEquals("john.doe@example.com", buyer1.getEmail());
        assertNull(buyer1.getPhone());
        assertNull(buyer1.getIdentityNumber());
        
        // Test buyer with all fields
        Buyer buyer2 = new Buyer("Jane", "Smith", "jane.smith@example.com", "+1234567890", "123456789");
        assertEquals("Jane", buyer2.getName());
        assertEquals("Smith", buyer2.getSurname());
        assertEquals("jane.smith@example.com", buyer2.getEmail());
        assertEquals("+1234567890", buyer2.getPhone());
        assertEquals("123456789", buyer2.getIdentityNumber());
    }
    
    @Test
    void testCurrencyEnum() {
        assertEquals("TRY", Currency.TRY.getCode());
        assertEquals("USD", Currency.USD.getCode());
        assertEquals("EUR", Currency.EUR.getCode());
        assertEquals("GBP", Currency.GBP.getCode());
        
        assertEquals(Currency.TRY, Currency.fromCode("TRY"));
        assertEquals(Currency.USD, Currency.fromCode("USD"));
        assertNull(Currency.fromCode("INVALID"));
        assertNull(Currency.fromCode(null));
    }
    
    @Test
    void testLocaleEnum() {
        assertEquals("tr", Locale.TR.getCode());
        assertEquals("en", Locale.EN.getCode());
        
        assertEquals(Locale.TR, Locale.fromCode("tr"));
        assertEquals(Locale.EN, Locale.fromCode("en"));
        assertNull(Locale.fromCode("INVALID"));
        assertNull(Locale.fromCode(null));
    }
    
    @Test
    void testMetadata() {
        Metadata metadata = new Metadata("key", "value");
        assertEquals("key", metadata.getKey());
        assertEquals("value", metadata.getValue());
        
        // Test toString
        assertTrue(metadata.toString().contains("key"));
        assertTrue(metadata.toString().contains("value"));
    }
    
    @Test
    void testTapsilatConfig() {
        TapsilatConfig config = new TapsilatConfig();
        assertNull(config.getBearerToken());
        // Base URL comes from environment if TAPSILAT_BASE_URL is set, otherwise defaults
        String expectedBaseUrl = System.getenv().getOrDefault("TAPSILAT_BASE_URL", "https://panel.tapsilat.dev");
        assertEquals(expectedBaseUrl, config.getBaseUrl());
        assertEquals(30000, config.getConnectionTimeout());
        assertEquals(60000, config.getReadTimeout());
        
        // Test custom configuration
        TapsilatConfig customConfig = new TapsilatConfig(
            "https://test-panel.tapsilat.dev", 15000, 30000);
        assertEquals("https://test-panel.tapsilat.dev", customConfig.getBaseUrl());
        assertEquals(15000, customConfig.getConnectionTimeout());
        assertEquals(30000, customConfig.getReadTimeout());
    }
    
    @Test
    void testTapsilatException() {
        TapsilatException exception1 = new TapsilatException("Test error");
        assertEquals("Test error", exception1.getMessage());
        assertEquals(0, exception1.getStatusCode());
        
        TapsilatException exception2 = new TapsilatException("Test error", 400);
        assertEquals("Test error", exception2.getMessage());
        assertEquals(400, exception2.getStatusCode());
        
        Exception cause = new RuntimeException("Cause");
        TapsilatException exception3 = new TapsilatException("Test error", cause);
        assertEquals("Test error", exception3.getMessage());
        assertEquals(cause, exception3.getCause());
        assertEquals(0, exception3.getStatusCode());
    }
    
    @Test
    void testOrderRequestValidation() {
        // Test null order request
        assertThrows(TapsilatException.class, () -> {
            client.createOrder(null);
        });
        
        // Test order request with null amount
        OrderRequest invalidRequest1 = new OrderRequest();
        invalidRequest1.setCurrency("TRY");
        invalidRequest1.setLocale("tr");
        invalidRequest1.setBuyer(new Buyer("John", "Doe", "john@example.com"));
        
        assertThrows(TapsilatException.class, () -> {
            client.createOrder(invalidRequest1);
        });
        
        // Test order request with null buyer
        OrderRequest invalidRequest2 = new OrderRequest();
        invalidRequest2.setAmount(new BigDecimal("100"));
        invalidRequest2.setCurrency("TRY");
        invalidRequest2.setLocale("tr");
        
        assertThrows(TapsilatException.class, () -> {
            client.createOrder(invalidRequest2);
        });
    }
    
    @Test
    void testClientClose() {
        // Test that client can be closed without exception
        assertDoesNotThrow(() -> {
            client.close();
        });
    }

    @Test
    void testOrderResponseMapping() throws Exception {
        // Test with actual API response structure
        String json = """
            {
              "order_id": "0d058ce3-3e55-47d8-8ea1-2b42a60b362c",
              "reference_id": "497b0212-3a51-4c52-aa75-0f93f613dd7c"
            }
            """;

        ObjectMapper mapper = new ObjectMapper();
        OrderResponse response = mapper.readValue(json, OrderResponse.class);

        assertNotNull(response);
        assertEquals("0d058ce3-3e55-47d8-8ea1-2b42a60b362c", response.getOrderId());
        assertEquals("497b0212-3a51-4c52-aa75-0f93f613dd7c", response.getReferenceId());
    }
} 