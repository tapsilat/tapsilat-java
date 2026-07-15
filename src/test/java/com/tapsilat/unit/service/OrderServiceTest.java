package com.tapsilat.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.common.BasketItem;
import com.tapsilat.model.common.Buyer;
import com.tapsilat.model.order.*;
import com.tapsilat.service.OrderService;
import com.tapsilat.unit.utils.MockHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private MockHttpClient httpClient;
    private OrderService orderService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://api.test");
        config.setBearerToken("test-token");

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        httpClient = new MockHttpClient();
        orderService = new OrderService(httpClient, config, objectMapper);
    }

    @Test
    void testCreateOrderSuccess() throws Exception {
        String jsonResponse = "{\"order_id\":\"mock-order-123\", \"reference_id\":\"mock-ref-123\", \"checkout_url\":\"https://checkout.test\"}";
        httpClient.setResponse(200, jsonResponse);

        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@test.com"));

        OrderResponse response = orderService.create(request);

        assertNotNull(response);
        assertEquals("mock-order-123", response.getOrderId());
        assertEquals("mock-ref-123", response.getReferenceId());
        assertEquals("https://checkout.test", response.getCheckoutUrl());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/create"));
    }

    @Test
    void testCreateOrderWithBasketItems() throws Exception {
        String jsonResponse = "{\"order_id\":\"order_basket\", \"reference_id\":\"ref_basket\", \"checkout_url\":\"https://checkout.test\"}";
        httpClient.setResponse(200, jsonResponse);

        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("30.49"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("Test", "User", "test@test.com"));

        BasketItem item1 = new BasketItem();
        item1.setId("B001");
        item1.setName("Item 1");
        item1.setPrice(10.0);
        item1.setItemType("PHYSICAL");

        BasketItem item2 = new BasketItem();
        item2.setId("B002");
        item2.setName("Item 2");
        item2.setPrice(20.49);

        request.setBasketItems(Arrays.asList(item1, item2));

        OrderResponse response = orderService.create(request);

        assertNotNull(response);
        assertEquals("order_basket", response.getOrderId());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/create"));
    }

    // ==================== get ====================

    @Test
    void testGetOrderSuccess() throws Exception {
        String jsonResponse = "{\"status\":8, \"reference_id\":\"mock-ref-123\", \"checkout_url\":\"https://checkout.test\"}";
        httpClient.setResponse(200, jsonResponse);

        OrderResponse response = orderService.get("mock-ref-123");

        assertNotNull(response);
        assertEquals("https://checkout.test", response.getCheckoutUrl());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/mock-ref-123"));
    }

    @Test
    void testGetOrderFailure() throws Exception {
        String errorJson = "{\"code\": 101160, \"error\": \"ORDER_ORDER_DETAIL_ORDER_NOT_FOUND\"}";
        httpClient.setResponse(400, errorJson);

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.get("mock-failed-ref");
        });

        assertTrue(exception.getMessage().contains("ORDER_ORDER_DETAIL_ORDER_NOT_FOUND"));
    }

    // ==================== getByConversationId ====================

    @Test
    void testGetOrderByConversationIdSuccess() throws Exception {
        String jsonResponse = "{\"checkout_url\":\"https://checkout.test\", \"status\":8}";
        httpClient.setResponse(200, jsonResponse);

        OrderResponse response = orderService.getByConversationId("mock-conv-id");

        assertNotNull(response);
        assertEquals("https://checkout.test", response.getCheckoutUrl());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/conversation/mock-conv-id"));
    }

    @Test
    void testGetOrderByConversationIdFailure() throws Exception {
        httpClient.setResponse(400, "{\"code\": 101160, \"error\": \"ORDER_ORDER_DETAIL_ORDER_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.getByConversationId("mock-conv-fail");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== list ====================

    @Test
    void testGetOrderList() throws Exception {
        String jsonResponse = "{\"page\":1, \"per_page\":3, \"rows\":[{},{},{}], \"total\":24, \"total_page\":8}";
        httpClient.setResponse(200, jsonResponse);

        Map<String, Object> response = orderService.list(1, 3, null, null, null, null, null, null);

        assertNotNull(response);
        assertEquals(1, ((Number) response.get("page")).intValue());
        assertEquals(3, ((Number) response.get("per_page")).intValue());

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/list"));
        assertTrue(captured.getUri().toString().contains("page=1"));
        assertTrue(captured.getUri().toString().contains("per_page=3"));
    }

    // ==================== getSubmerchants ====================

    @Test
    void testGetSubmerchants() throws Exception {
        String jsonResponse = "{\"page\":1, \"per_page\":2, \"row\":[{},{}], \"total\":10, \"total_pages\":5}";
        httpClient.setResponse(200, jsonResponse);

        Map<String, Object> response = orderService.getSubmerchants(1, 2);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/submerchants"));
    }

    // ==================== cancel ====================

    @Test
    void testCancelOrderSuccess() throws Exception {
        String jsonResponse = "{\"is_success\":true, \"error\":\"ORDER_CANCEL_SUCCESS\", \"status\":\"101645\"}";
        httpClient.setResponse(200, jsonResponse);

        Map<String, Object> response = orderService.cancel("mock-ref-123");

        assertNotNull(response);
        assertTrue((Boolean) response.get("is_success"));
        assertEquals("ORDER_CANCEL_SUCCESS", response.get("error"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/cancel"));
    }

    @Test
    void testCancelOrderNotFound() throws Exception {
        httpClient.setResponse(400, "{\"code\": 101550, \"error\": \"ORDER_CANCEL_ORDER_GET_ORDER_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.cancel("mock-not-found");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== refund ====================

    @Test
    void testRefundOrderSuccess() throws Exception {
        String jsonResponse = "{\"is_success\":true, \"error\":\"REFUND_SUCCESSFUL\"}";
        httpClient.setResponse(200, jsonResponse);

        RefundOrderRequest request = new RefundOrderRequest();
        request.setReferenceId("mock-ref-123");
        request.setAmount(new BigDecimal("50.0"));

        Map<String, Object> response = orderService.refund(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("is_success"));
        assertEquals("REFUND_SUCCESSFUL", response.get("error"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/refund"));
    }

    @Test
    void testRefundOrderWithOptionalFields() throws Exception {
        httpClient.setResponse(200, "{\"is_success\":true}");

        RefundOrderRequest request = new RefundOrderRequest();
        request.setReferenceId("ref456");
        request.setAmount(new BigDecimal("100.0"));
        request.setOrderItemId("item002");
        request.setOrderItemPaymentId("payment002");

        Map<String, Object> response = orderService.refund(request);
        assertNotNull(response);
    }

    @Test
    void testRefundOrderFailure() throws Exception {
        httpClient.setResponse(400, "{\"code\": 201010, \"error\": \"REFUND_VALIDATION_ERROR\"}");

        RefundOrderRequest request = new RefundOrderRequest();
        request.setReferenceId("order_ref_invalid");
        request.setAmount(BigDecimal.ZERO);

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.refund(request);
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== refundAll ====================

    @Test
    void testRefundAllSuccess() throws Exception {
        httpClient.setResponse(200, "{\"is_success\":true, \"error\":\"REFUND_ALL_SUCCESSFUL\"}");

        Map<String, Object> response = orderService.refundAll("order_ref_xyz");

        assertNotNull(response);
        assertTrue((Boolean) response.get("is_success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/refund-all"));
    }

    @Test
    void testRefundAllFailure() throws Exception {
        httpClient.setResponse(400, "{\"code\": 201020, \"error\": \"ORDER_NOT_FOUND_FOR_REFUND_ALL\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.refundAll("order_ref_nonexistent");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== getPaymentDetails ====================

    @Test
    void testGetPaymentDetailsById() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"mock-payment-details-id\"}");

        Map<String, Object> response = orderService.getPaymentDetails("mock-ref-id", null);

        assertNotNull(response);
        assertEquals("mock-payment-details-id", response.get("id"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/mock-ref-id/payment-details"));
    }

    @Test
    void testGetPaymentDetailsWithConversationId() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"mock-payment-details-id-conv\"}");

        Map<String, Object> response = orderService.getPaymentDetails("mock-ref-id", "mock-conv-id");

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/payment-details"));
    }

    @Test
    void testGetPaymentDetailsNotFound() throws Exception {
        httpClient.setResponse(400,
                "{\"code\": 101230, \"error\": \"ORDER_ORDER_PAYMENT_DETAIL_ORDER_DETAIL_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.getPaymentDetails("mock-ref-id", null);
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== getStatus ====================

    @Test
    void testGetStatusSuccess() throws Exception {
        String jsonResponse = "{\"status\":\"Refunded\"}";
        httpClient.setResponse(200, jsonResponse);

        Map<String, Object> response = orderService.getStatus("mock-ref-123");

        assertNotNull(response);
        assertEquals("Refunded", response.get("status"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/mock-ref-123/status"));
    }

    @Test
    void testGetStatusNotFound() throws Exception {
        httpClient.setResponse(400, "{\"code\": 100810, \"error\": \"ORDER_GET_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.getStatus("mock-ref-123");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== getTransactions ====================

    @Test
    void testGetTransactionsSuccess() throws Exception {
        httpClient.setResponse(200, "{\"transactions\":[{\"id\":\"mock-transaction-1\"}]}");

        Map<String, Object> response = orderService.getTransactions("mock-ref-123");
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/mock-ref-123/transactions"));
    }

    @Test
    void testGetTransactionsNotFound() throws Exception {
        httpClient.setResponse(400, "{\"code\": 101260, \"error\": \"ORDER_GET_ORDER_TXS_GET_ORDER_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.getTransactions("mock-ref-123");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== accounting ====================

    @Test
    void testAccountingSuccess() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrderAccountingRequest request = new OrderAccountingRequest("mock-ref-123");
        Map<String, Object> response = orderService.accounting(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/accounting"));
    }

    // ==================== postAuth ====================

    @Test
    void testPostAuthSuccess() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrderPostAuthRequest request = new OrderPostAuthRequest(new BigDecimal("50.0"), "mock-ref-123");
        Map<String, Object> response = orderService.postAuth(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/postauth"));
    }

    // ==================== systemOrderStatuses ====================

    @Test
    void testGetSystemOrderStatuses() throws Exception {
        httpClient.setResponse(200, "{\"statuses\":[]}");

        Map<String, Object> response = orderService.getSystemOrderStatuses();

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/system/order-statuses"));
    }

    // ==================== Term Operations ====================

    @Test
    void testGetTerm() throws Exception {
        httpClient.setResponse(200, "{\"term_reference_id\":\"abc\"}");

        Map<String, Object> response = orderService.getTerm("abc");

        assertNotNull(response);
        assertEquals("abc", response.get("term_reference_id"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/term"));
        assertTrue(captured.getUri().toString().contains("term_reference_id=abc"));
    }

    @Test
    void testCreateTermSuccess() throws Exception {
        httpClient.setResponse(200, "{\"message\":\"ORDER_ADD_PAYMENT_TERM_SUCCESS\", \"code\":156050}");

        OrderPaymentTermCreateRequest request = new OrderPaymentTermCreateRequest();
        request.setOrderId("order123");
        request.setTermReferenceId("term-ref-create");
        request.setAmount(new BigDecimal("200"));
        request.setDueDate("2025-10-10 00:00:00");
        request.setTermSequence(2);
        request.setRequired(false);
        request.setStatus("active");

        Map<String, Object> response = orderService.createTerm(request);

        assertNotNull(response);
        assertEquals("ORDER_ADD_PAYMENT_TERM_SUCCESS", response.get("message"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/term"));
    }

    @Test
    void testCreateTermFailure_ExceedsOrderAmount() throws Exception {
        httpClient.setResponse(400,
                "{\"code\": 156025, \"error\": \"ORDER_ADD_PAYMENT_TERM_AMOUNT_EXCEEDS_ORDER_AMOUNT\"}");

        OrderPaymentTermCreateRequest request = new OrderPaymentTermCreateRequest();
        request.setOrderId("order123");
        request.setAmount(new BigDecimal("600"));

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.createTerm(request);
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void testDeleteTerm() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = orderService.deleteTerm("order123", "term-ref-abc");

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("DELETE", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/term"));
    }

    @Test
    void testUpdateTerm() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrderPaymentTermUpdateRequest request = new OrderPaymentTermUpdateRequest();
        request.setTermReferenceId("abc");
        request.setAmount(new BigDecimal("100.0"));
        request.setDueDate("2023-12-31");
        request.setRequired(true);
        request.setStatus("pending");
        request.setTermSequence(1);

        Map<String, Object> response = orderService.updateTerm(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("PATCH", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/term"));
    }

    @Test
    void testRefundTerm() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrderTermRefundRequest request = new OrderTermRefundRequest();
        request.setTermId("term-id");
        request.setAmount(new BigDecimal("25"));
        request.setReferenceId("ref-123");

        Map<String, Object> response = orderService.refundTerm(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/term/refund"));
    }



    // ==================== terminate ====================

    @Test
    void testTerminateOrderSuccess() throws Exception {
        httpClient.setResponse(200, "{\"message\":\"ORDER_TERMINATE_ORDER_SUCCESS\", \"code\":338100}");

        Map<String, Object> response = orderService.terminate("mock-ref-id");

        assertNotNull(response);
        assertEquals("ORDER_TERMINATE_ORDER_SUCCESS", response.get("message"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/terminate"));
    }

    @Test
    void testTerminateOrderNotFound() throws Exception {
        httpClient.setResponse(400, "{\"code\": 338000, \"error\": \"ORDER_TERMINATE_ORDER_NOT_FOUND\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.terminate("mock-ref-id");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== manualCallback ====================

    @Test
    void testManualCallbackSuccess() throws Exception {
        httpClient.setResponse(200, "{\"message\":\"ORDER_MANUAL_CALLBACK_SUCCESS\", \"code\":337100}");

        OrderManualCallbackRequest request = new OrderManualCallbackRequest("mock-ref-id");
        request.setConversationId("mock-conv-id");

        Map<String, Object> response = orderService.manualCallback(request);

        assertNotNull(response);
        assertEquals("ORDER_MANUAL_CALLBACK_SUCCESS", response.get("message"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/callback"));
    }

    @Test
    void testManualCallbackFailed() throws Exception {
        httpClient.setResponse(400, "{\"code\": 12000, \"error\": \"ACTION_FAILED\"}");

        OrderManualCallbackRequest request = new OrderManualCallbackRequest("mock-ref-id");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.manualCallback(request);
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== relatedUpdate ====================

    @Test
    void testRelatedUpdateSuccess() throws Exception {
        httpClient.setResponse(200,
                "{\"message\":\"ORDER_UPDATE_ORDER_SUCCESS\", \"code\":156170, \"is_success\":true}");

        Map<String, Object> response = orderService.relatedUpdate("mock-ref-id", "mock-related-ref-id");

        assertNotNull(response);
        assertTrue((Boolean) response.get("is_success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("PATCH", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/releated"));
    }

    @Test
    void testRelatedUpdateNotFound() throws Exception {
        httpClient.setResponse(400, "{\"code\": 12000, \"error\": \"ACTION_FAILED\"}");

        TapsilatException exception = assertThrows(TapsilatException.class, () -> {
            orderService.relatedUpdate("mock-ref-id", "mock-related-ref-id");
        });
        assertNotNull(exception.getMessage());
    }

    // ==================== Basket Item Operations ====================

    @Test
    void testAddBasketItem() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        BasketItem item = new BasketItem();
        item.setId("BI101");
        item.setName("Binocular");
        item.setPrice(19.99);

        AddBasketItemRequest request = new AddBasketItemRequest("order-ref-123", item);
        Map<String, Object> response = orderService.addBasketItem(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/basket-item"));
    }

    @Test
    void testRemoveBasketItem() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        RemoveBasketItemRequest request = new RemoveBasketItemRequest("order-ref-123", "BI101");
        Map<String, Object> response = orderService.removeBasketItem(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("DELETE", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/basket-item"));
    }

    @Test
    void testUpdateBasketItem() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        BasketItem item = new BasketItem();
        item.setId("BI101");
        item.setName("Updated Binocular");
        item.setPrice(24.99);

        UpdateBasketItemRequest request = new UpdateBasketItemRequest("order-ref-123", item);
        Map<String, Object> response = orderService.updateBasketItem(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("PATCH", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/basket-item"));
    }

    // ==================== getOrganizationSettings ====================

    @Test
    void testGetOrganizationSettings() throws Exception {
        httpClient.setResponse(200, "{\"settings\":{}}");

        Map<String, Object> response = orderService.getOrganizationSettings();

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/settings"));
    }

    // ==================== new methods ====================

    @Test
    void testGetPayments() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = orderService.getPayments("order123");
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/order123/payments"));
    }

    @Test
    void testPdf() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = orderService.pdf("ref123");
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/ref123/pdf"));
    }

    @Test
    void testExcel() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = orderService.excel("ref123");
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/ref123/excel"));
    }

    @Test
    void testRefundRequest() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        com.tapsilat.model.order.RefundRequestModel req = new com.tapsilat.model.order.RefundRequestModel();
        req.setOrderId("order123");

        Map<String, Object> response = orderService.refundRequest(req);
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/refund-request"));
    }

    @Test
    void testAddOip() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        com.tapsilat.model.order.AddOrderOipRequest req = new com.tapsilat.model.order.AddOrderOipRequest();
        req.setOrderId("order123");

        Map<String, Object> response = orderService.addOip(req);
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/order/oip"));
    }
}
