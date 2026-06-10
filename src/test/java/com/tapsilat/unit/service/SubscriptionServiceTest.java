package com.tapsilat.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.model.subscription.*;
import com.tapsilat.service.SubscriptionService;
import com.tapsilat.unit.utils.MockHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionServiceTest {

    private MockHttpClient httpClient;
    private SubscriptionService subscriptionService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://api.test");
        config.setBearerToken("test-token");

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        httpClient = new MockHttpClient();
        subscriptionService = new SubscriptionService(httpClient, config, objectMapper);
    }

    // ==================== create ====================

    @Test
    void testCreateSubscriptionSuccess() throws Exception {
        String jsonResponse = "{\"reference_id\":\"sub-ref-new\", \"code\":100, \"message\":\"Success\"}";
        httpClient.setResponse(200, jsonResponse);

        SubscriptionCreateRequest request = new SubscriptionCreateRequest();
        request.setTitle("Monthly Plan");
        request.setAmount(new BigDecimal("100.0"));
        request.setCurrency("TRY");
        request.setPeriod(30);

        SubscriptionUser user = new SubscriptionUser();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("test@test.com");
        request.setUser(user);

        SubscriptionCreateResponse response = subscriptionService.create(request);

        assertNotNull(response);
        assertEquals("sub-ref-new", response.getReferenceId());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/subscription/create"));
    }

    // ==================== get ====================

    @Test
    void testGetSubscriptionSuccess() throws Exception {
        String jsonResponse = "{\"external_reference_id\":\"ext-ref-123\", \"title\":\"My Subscription\"}";
        httpClient.setResponse(200, jsonResponse);

        SubscriptionGetRequest request = new SubscriptionGetRequest();
        request.setReferenceId("sub-ref-123");
        SubscriptionDetail response = subscriptionService.get(request);

        assertNotNull(response);
        assertEquals("ext-ref-123", response.getExternalReferenceId());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/subscription"));
    }

    // ==================== list ====================

    @Test
    void testListSubscriptionsSuccess() throws Exception {
        String jsonResponse = "{\"page\":1, \"per_page\":10, \"items\":[], \"total\":0}";
        httpClient.setResponse(200, jsonResponse);

        List<SubscriptionListItem> response = subscriptionService.list(1, 10);
        assertNotNull(response);
        assertTrue(response.isEmpty());

        Map<String, Object> rawResponse = subscriptionService.listResponse(1, 10);

        assertNotNull(rawResponse);
        assertEquals(1, ((Number) rawResponse.get("page")).intValue());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/subscription/list"));
        assertTrue(captured.getUri().toString().contains("page=1"));
        assertTrue(captured.getUri().toString().contains("per_page=10"));
    }

    // ==================== cancel ====================

    @Test
    void testCancelSubscription() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        SubscriptionCancelRequest request = new SubscriptionCancelRequest();
        request.setReferenceId("sub-ref-123");

        Map<String, Object> response = subscriptionService.cancel(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/subscription/cancel"));
    }

    // ==================== redirect ====================

    @Test
    void testRedirectSubscription() throws Exception {
        httpClient.setResponse(200, "{\"url\":\"https://redirect.example.com\"}");

        SubscriptionRedirectRequest request = new SubscriptionRedirectRequest();
        request.setSubscriptionId("sub-ref-123");

        SubscriptionRedirectResponse response = subscriptionService.redirect(request);

        assertNotNull(response);
        assertEquals("https://redirect.example.com", response.getUrl());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/subscription/redirect"));
    }
}
