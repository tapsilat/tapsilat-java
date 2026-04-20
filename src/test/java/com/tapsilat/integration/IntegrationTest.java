package com.tapsilat.integration;

import com.tapsilat.TapsilatClient;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.order.*;
import com.tapsilat.model.common.*;
import com.tapsilat.model.subscription.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests that run against the real Tapsilat API.
 * Reads TAPSILAT_API_KEY from environment variable or .env file.
 * Mirrors tapsilat-py/tests/integration/integration_test.py
 */
public class IntegrationTest {

    private TapsilatClient client;
    private String token;

    @BeforeEach
    public void setUp() {
        token = System.getenv("TAPSILAT_API_KEY");

        if (token == null) {
            token = loadFromEnvFile("TAPSILAT_API_KEY");
        }

        if (token == null) {
            System.out.println("Warning: TAPSILAT_API_KEY not set. Skipping setup.");
            return;
        }

        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://panel.tapsilat.dev/api/v1");
        config.setBearerToken(token);

        client = new TapsilatClient(config);
    }

    /**
     * Reads a key from the .env file in the project root.
     * Supports KEY=VALUE format (no quotes handling needed for simple values).
     */
    private String loadFromEnvFile(String key) {
        // Try multiple possible .env locations
        String[] paths = { ".env", "../.env" };
        for (String path : paths) {
            File envFile = new File(path);
            if (envFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(envFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty() || line.startsWith("#"))
                            continue;
                        int eqIndex = line.indexOf('=');
                        if (eqIndex > 0) {
                            String k = line.substring(0, eqIndex).trim();
                            String v = line.substring(eqIndex + 1).trim();
                            if (k.equals(key)) {
                                return v;
                            }
                        }
                    }
                } catch (IOException e) {
                    // Silently ignore read errors
                }
            }
        }
        return null;
    }

    // ==================== Scenario 1: Basic Order ====================

    @Test
    public void testScenario1_BasicOrder() throws Exception {
        if (token == null) {
            skipTest("testScenario1_BasicOrder");
            return;
        }

        OrderCreateRequest order = new OrderCreateRequest();
        order.setAmount(BigDecimal.valueOf(100.00));
        order.setCurrency("TRY");
        order.setLocale("tr");
        order.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        OrderResponse response = client.orders().create(order);
        assertNotNull(response);
        assertNotNull(response.getReferenceId());
    }

    // ==================== Scenario 2: Order with Basket Items ====================

    @Test
    public void testScenario2_OrderWithBasketItems() throws Exception {
        if (token == null) {
            skipTest("testScenario2_OrderWithBasketItems");
            return;
        }

        OrderCreateRequest order = new OrderCreateRequest();
        order.setAmount(BigDecimal.valueOf(30.49));
        order.setCurrency("TRY");
        order.setLocale("tr");
        order.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        BasketItem item1 = new BasketItem();
        item1.setId("B001");
        item1.setName("Item 1");
        item1.setPrice(10.00);
        item1.setItemType("PHYSICAL");

        BasketItem item2 = new BasketItem();
        item2.setId("B002");
        item2.setName("Item 2");
        item2.setPrice(20.49);
        item2.setItemType("PHYSICAL");

        order.setBasketItems(Arrays.asList(item1, item2));

        OrderResponse response = client.orders().create(order);
        assertNotNull(response);
        assertNotNull(response.getReferenceId());
    }

    // ==================== Scenario 3: Order with Addresses ====================

    @Test
    public void testScenario3_OrderWithAddresses() throws Exception {
        if (token == null) {
            skipTest("testScenario3_OrderWithAddresses");
            return;
        }

        OrderCreateRequest order = new OrderCreateRequest();
        order.setAmount(BigDecimal.valueOf(25.00));
        order.setCurrency("TRY");
        order.setLocale("tr");
        order.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        BillingAddress billing = new BillingAddress();
        billing.setAddress("123 Main St");
        billing.setCity("Istanbul");
        billing.setCountry("TR");
        billing.setContactName("John Doe");
        billing.setZipCode("34000");
        order.setBillingAddress(billing);

        ShippingAddress shipping = new ShippingAddress();
        shipping.setAddress("456 Oak Ave");
        shipping.setCity("Istanbul");
        shipping.setCountry("TR");
        shipping.setContactName("Jane Doe");
        shipping.setZipCode("34001");
        order.setShippingAddress(shipping);

        OrderResponse response = client.orders().create(order);
        assertNotNull(response);
        assertNotNull(response.getReferenceId());
    }

    // ==================== Scenario 4: Installments ====================

    @Test
    public void testScenario4_Installments() throws Exception {
        if (token == null) {
            skipTest("testScenario4_Installments");
            return;
        }

        OrderCreateRequest order = new OrderCreateRequest();
        order.setAmount(BigDecimal.valueOf(1200.00));
        order.setCurrency("TRY");
        order.setLocale("tr");
        order.setBuyer(new Buyer("John", "Doe", "test@example.com"));
        order.setEnabledInstallments(Arrays.asList(2, 3, 6, 9));
        order.setPaymentMethods(true);
        order.setPaymentOptions(Arrays.asList("credit_card", "cash"));

        OrderResponse response = client.orders().create(order);
        assertNotNull(response);
        assertNotNull(response.getReferenceId());
    }

    // ==================== Scenario 5: Order List ====================

    @Test
    public void testScenario5_GetOrders() throws Exception {
        if (token == null) {
            skipTest("testScenario5_GetOrders");
            return;
        }

        try {
            Map<String, Object> response = client.orders().list(1, 10, null, null, null, null, null);
            assertNotNull(response);
        } catch (TapsilatException e) {
            fail("API Error: " + e.getMessage());
        }
    }

    // ==================== Scenario 6: Organization Settings ====================

    @Test
    public void testScenario6_OrganizationSettings() throws Exception {
        if (token == null) {
            skipTest("testScenario6_OrganizationSettings");
            return;
        }

        Map<String, Object> settings = client.orders().getOrganizationSettings();
        assertNotNull(settings);
    }

    // ==================== Scenario 7: Subscription Lifecycle ====================

    @Test
    public void testScenario7_SubscriptionLifecycle() throws Exception {
        if (token == null) {
            skipTest("testScenario7_SubscriptionLifecycle");
            return;
        }

        // Create Subscription
        SubscriptionCreateRequest subRequest = new SubscriptionCreateRequest();
        subRequest.setAmount(new BigDecimal("100.0"));
        subRequest.setCurrency("TRY");
        subRequest.setCycle(12);
        subRequest.setPeriod(1);
        subRequest.setPaymentDate(1);
        subRequest.setTitle("Monthly Subscription");
        subRequest.setExternalReferenceId("ext_ref_java_test");
        subRequest.setSuccessUrl("https://example.com/success");
        subRequest.setFailureUrl("https://example.com/failure");

        SubscriptionUser user = new SubscriptionUser();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPhone("+905551234567");
        user.setIdentityNumber("12345678901");
        subRequest.setUser(user);

        SubscriptionCreateResponse createResponse = client.subscriptions().create(subRequest);
        assertNotNull(createResponse);
        assertNotNull(createResponse.getReferenceId());

        String subRefId = createResponse.getReferenceId();

        // Get Subscription
        SubscriptionGetRequest getRequest = new SubscriptionGetRequest();
        getRequest.setReferenceId(subRefId);
        SubscriptionDetail subscription = client.subscriptions().get(getRequest);
        assertNotNull(subscription);

        // List Subscriptions
        Map<String, Object> subscriptions = client.subscriptions().list(1, 10);
        assertNotNull(subscriptions);

        // Redirect Subscription
        SubscriptionRedirectRequest redirectRequest = new SubscriptionRedirectRequest();
        redirectRequest.setSubscriptionId(subRefId);
        SubscriptionRedirectResponse redirectResponse = client.subscriptions().redirect(redirectRequest);
        assertNotNull(redirectResponse);
        assertNotNull(redirectResponse.getUrl());

        // Cancel Subscription
        SubscriptionCancelRequest cancelRequest = new SubscriptionCancelRequest();
        cancelRequest.setReferenceId(subRefId);
        Map<String, Object> cancelResponse = client.subscriptions().cancel(cancelRequest);
        assertNotNull(cancelResponse);
    }

    // ==================== Scenario 8: Order Operations Smoke Test
    // ====================

    @Test
    public void testScenario8_OrderOperationsSmoke() throws Exception {
        if (token == null) {
            skipTest("testScenario8_OrderOperationsSmoke");
            return;
        }

        // get_system_order_statuses
        try {
            Map<String, Object> statuses = client.orders().getSystemOrderStatuses();
            assertNotNull(statuses);
        } catch (TapsilatException ignored) {
        }

        // get_order_submerchants
        try {
            Map<String, Object> merchants = client.orders().getSubmerchants(1, 10);
            assertNotNull(merchants);
        } catch (TapsilatException ignored) {
        }

        String dummyRef = "dummy_ref_123";

        // get_order_by_conversation_id
        try {
            client.orders().getByConversationId(dummyRef);
        } catch (TapsilatException ignored) {
        }

        // order_accounting
        try {
            client.orders().accounting(new OrderAccountingRequest(dummyRef));
        } catch (TapsilatException ignored) {
        }

        // order_postauth
        try {
            client.orders().postAuth(new OrderPostAuthRequest(new BigDecimal("10.0"), dummyRef));
        } catch (TapsilatException ignored) {
        }

        // get_order_status
        try {
            client.orders().getStatus(dummyRef);
        } catch (TapsilatException ignored) {
        }

        // get_order_transactions
        try {
            client.orders().getTransactions(dummyRef);
        } catch (TapsilatException ignored) {
        }

        // cancel_order
        try {
            client.orders().cancel(dummyRef);
        } catch (TapsilatException ignored) {
        }

        // get_order_payment_details
        try {
            client.orders().getPaymentDetails(dummyRef, null);
        } catch (TapsilatException ignored) {
        }
    }

    private void skipTest(String testName) {
        System.out.println("Skipping " + testName + ": No API Key");
    }
}
