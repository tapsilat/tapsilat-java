package com.tapsilat.unit.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.model.subscription.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionModelTest {
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
    }

    @Test
    void testSubscriptionCreateRequestSerialization() throws JsonProcessingException {
        SubscriptionUser buyer = new SubscriptionUser();
        buyer.setFirstName("Sub");
        buyer.setLastName("User");
        buyer.setEmail("subuser@example.com");

        SubscriptionCreateRequest request = new SubscriptionCreateRequest();
        request.setAmount(new BigDecimal("99.99"));
        request.setCurrency("TRY");
        request.setUser(buyer);
        request.setCycle(1);
        request.setPeriod(1);
        request.setExternalReferenceId("sub-ref-123");
        request.setSuccessUrl("http://callback.test");
        request.setPriceOption(new SubscriptionPriceOption(2, new BigDecimal("49.99")));

        String json = mapper.writeValueAsString(request);
        JsonNode node = mapper.readTree(json);

        assertEquals(99.99, node.get("amount").asDouble());
        assertEquals("TRY", node.get("currency").asText());
        assertEquals(1, node.get("cycle").asInt());
        assertEquals(1, node.get("period").asInt());
        assertEquals("sub-ref-123", node.get("external_reference_id").asText());
        assertEquals("http://callback.test", node.get("success_url").asText());
        assertEquals("Sub", node.get("user").get("first_name").asText());
        assertEquals(2, node.get("price_option").get("count").asInt());
        assertEquals(49.99, node.get("price_option").get("price").asDouble());
    }

    @Test
    void testSubscriptionCancelRequestSerialization() throws JsonProcessingException {
        SubscriptionCancelRequest request = new SubscriptionCancelRequest();
        request.setReferenceId("ref123");
        
        String json = mapper.writeValueAsString(request);
        JsonNode node = mapper.readTree(json);
        
        assertEquals("ref123", node.get("reference_id").asText());
    }
}
