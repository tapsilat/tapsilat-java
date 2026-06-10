package com.tapsilat.unit.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests JSON serialization of all order-related request and response DTOs.
 * Mirrors test_order.py DTO serialization tests.
 */
class OrderDTOSerializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
    }

    // ==================== OrderCreateRequest ====================

    @Test
    void testOrderCreateRequest_BasicSerialization() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        String json = objectMapper.writeValueAsString(request);
        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals(100, ((Number) map.get("amount")).intValue());
        assertEquals("TRY", map.get("currency"));
        assertEquals("tr", map.get("locale"));

        @SuppressWarnings("unchecked")
        Map<String, Object> buyer = (Map<String, Object>) map.get("buyer");
        assertEquals("John", buyer.get("name"));
        assertEquals("Doe", buyer.get("surname"));
        assertEquals("test@example.com", buyer.get("email"));
    }

    @Test
    void testOrderCreateRequest_OmitsNullFields() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("50"));
        request.setCurrency("USD");
        request.setLocale("en");
        request.setBuyer(new Buyer("Jane", "Smith", "jane@test.com"));

        String json = objectMapper.writeValueAsString(request);

        assertFalse(json.contains("conversation_id"));
        assertFalse(json.contains("metadata"));
        assertFalse(json.contains("basket_items"));
        assertFalse(json.contains("shipping_address"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testOrderCreateRequest_FullSerialization() throws Exception {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("150"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));
        request.setConversationId("conv-id-123");
        request.setEnabledInstallments(Arrays.asList(3, 6));
        request.setExternalReferenceId("ext-ref");
        request.setPaymentMode("card");
        request.setRedirectSuccessUrl("https://example.com/success");
        request.setRedirectFailureUrl("https://example.com/fail");
        request.setThreeDForce(true);

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("conv-id-123", map.get("conversation_id"));
        assertEquals("ext-ref", map.get("external_reference_id"));
        assertEquals("card", map.get("payment_mode"));
        assertEquals("https://example.com/success", map.get("redirect_success_url"));
        assertEquals("https://example.com/fail", map.get("redirect_failure_url"));
        assertTrue((Boolean) map.get("three_d_force"));
    }

    // ==================== OrderResponse ====================

    @Test
    void testOrderResponse_Deserialization() throws Exception {
        String json = "{\"order_id\":\"o123\", \"reference_id\":\"r456\", \"checkout_url\":\"https://checkout.test\"}";
        OrderResponse response = objectMapper.readValue(json, OrderResponse.class);

        assertEquals("o123", response.getOrderId());
        assertEquals("r456", response.getReferenceId());
        assertEquals("https://checkout.test", response.getCheckoutUrl());
    }

    @Test
    void testOrderResponse_IgnoresUnknownFields() throws Exception {
        String json = "{\"order_id\":\"o123\", \"unknown_field\":\"value\"}";
        OrderResponse response = objectMapper.readValue(json, OrderResponse.class);
        assertEquals("o123", response.getOrderId());
    }

    // ==================== BasketItem ====================

    @Test
    @SuppressWarnings("unchecked")
    void testBasketItem_Serialization() throws Exception {
        BasketItem item = new BasketItem();
        item.setId("BI101");
        item.setName("Binocular");
        item.setPrice(19.99);
        item.setItemType("PHYSICAL");

        String json = objectMapper.writeValueAsString(item);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("BI101", map.get("id"));
        assertEquals("Binocular", map.get("name"));
        assertEquals(19.99, ((Number) map.get("price")).doubleValue());
        assertEquals("PHYSICAL", map.get("item_type"));
        assertNull(map.get("category1"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testBasketItem_AllFields() throws Exception {
        BasketItemPayer payer = new BasketItemPayer();
        payer.setTitle("Company");

        BasketItem item = new BasketItem();
        item.setId("item1");
        item.setPrice(100.0);
        item.setName("Product");
        item.setCategory1("C1");
        item.setCategory2("C2");
        item.setItemType("PHYSICAL");
        item.setSubMerchantKey("key1");
        item.setSubMerchantPrice("100.0");
        item.setCoupon("SAVE10");
        item.setCouponDiscount(10.0);
        item.setQuantity(1);
        item.setQuantityFloat(1.0);
        item.setQuantityUnit("pcs");
        item.setPaidAmount(90.0);
        item.setData("extra");
        item.setPayer(payer);
        item.setCommissionAmount(5.0);
        item.setMcc("1234");

        String json = objectMapper.writeValueAsString(item);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("item1", map.get("id"));
        assertEquals(5.0, ((Number) map.get("commission_amount")).doubleValue());
        assertEquals(1.0, ((Number) map.get("quantity_float")).doubleValue());
        assertEquals("Company", ((Map<String, Object>) map.get("payer")).get("title"));
    }

    // ==================== BillingAddress ====================

    @Test
    @SuppressWarnings("unchecked")
    void testBillingAddress_Serialization() throws Exception {
        BillingAddress billing = new BillingAddress();
        billing.setAddress("uskudar");
        billing.setCity("Istanbul");
        billing.setCountry("TR");
        billing.setContactName("Jane Doe");

        String json = objectMapper.writeValueAsString(billing);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("uskudar", map.get("address"));
        assertEquals("Istanbul", map.get("city"));
        assertEquals("TR", map.get("country"));
        assertEquals("Jane Doe", map.get("contact_name"));
        assertNull(map.get("zip_code"));
    }

    // ==================== Metadata ====================

    @Test
    @SuppressWarnings("unchecked")
    void testMetadata_Serialization() throws Exception {
        Metadata meta = new Metadata("key", "value");

        String json = objectMapper.writeValueAsString(meta);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("key", map.get("key"));
        assertEquals("value", map.get("value"));
    }

    // ==================== OrderCard ====================

    @Test
    @SuppressWarnings("unchecked")
    void testOrderCard_Serialization() throws Exception {
        OrderCard card = new OrderCard();
        card.setCardId("123456789");
        card.setCardSequence(1);

        String json = objectMapper.writeValueAsString(card);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("123456789", map.get("card_id"));
        assertEquals(1, ((Number) map.get("card_sequence")).intValue());
    }

    // ==================== RefundOrderRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testRefundOrderRequest_Serialization() throws Exception {
        RefundOrderRequest request = new RefundOrderRequest();
        request.setAmount(new BigDecimal("50.0"));
        request.setReferenceId("ref123");
        request.setOrderItemId("item001");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("ref123", map.get("reference_id"));
        assertEquals("item001", map.get("order_item_id"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void testRefundOrderRequest_FullSerialization() throws Exception {
        RefundOrderRequest request = new RefundOrderRequest();
        request.setAmount(new BigDecimal("100.0"));
        request.setReferenceId("ref456");
        request.setOrderItemId("item002");
        request.setOrderItemPaymentId("payment002");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("ref456", map.get("reference_id"));
        assertEquals("item002", map.get("order_item_id"));
        assertEquals("payment002", map.get("order_item_payment_id"));
    }

    // ==================== OrderAccountingRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testOrderAccountingRequest_Serialization() throws Exception {
        OrderAccountingRequest request = new OrderAccountingRequest("ref123");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("ref123", map.get("order_reference_id"));
    }

    // ==================== OrderPostAuthRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testOrderPostAuthRequest_Serialization() throws Exception {
        OrderPostAuthRequest request = new OrderPostAuthRequest(new BigDecimal("50.0"), "ref123");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("ref123", map.get("reference_id"));
    }

    // ==================== OrderPaymentTermCreateRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testOrderPaymentTermCreateRequest_Serialization() throws Exception {
        OrderPaymentTermCreateRequest request = new OrderPaymentTermCreateRequest();
        request.setOrderId("order123");
        request.setTermReferenceId("term-ref-create");
        request.setAmount(new BigDecimal("200"));
        request.setDueDate("2025-10-10 00:00:00");
        request.setTermSequence(2);
        request.setRequired(false);
        request.setStatus("active");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("order123", map.get("order_id"));
        assertEquals("term-ref-create", map.get("term_reference_id"));
        assertEquals(2, ((Number) map.get("term_sequence")).intValue());
        assertEquals(false, map.get("required"));
        assertEquals("active", map.get("status"));
    }

    // ==================== OrderManualCallbackRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testOrderManualCallbackRequest_Serialization() throws Exception {
        OrderManualCallbackRequest request = new OrderManualCallbackRequest("ref-id");
        request.setConversationId("conv-id");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("ref-id", map.get("reference_id"));
        assertEquals("conv-id", map.get("conversation_id"));
    }

    // ==================== AddBasketItemRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testAddBasketItemRequest_Serialization() throws Exception {
        BasketItem item = new BasketItem();
        item.setId("item-1");
        item.setName("Widget");
        item.setPrice(9.99);

        AddBasketItemRequest request = new AddBasketItemRequest("order-ref", item);

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("order-ref", map.get("order_reference_id"));
        assertNotNull(map.get("basket_item"));
    }

    // ==================== RemoveBasketItemRequest ====================

    @Test
    @SuppressWarnings("unchecked")
    void testRemoveBasketItemRequest_Serialization() throws Exception {
        RemoveBasketItemRequest request = new RemoveBasketItemRequest("order-ref", "item-1");

        String json = objectMapper.writeValueAsString(request);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        assertEquals("order-ref", map.get("order_reference_id"));
        assertEquals("item-1", map.get("basket_item_id"));
    }
}
