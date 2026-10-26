package com.tapsilat.unit.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.ElementsConstants;
import com.tapsilat.exception.ApiException;
import com.tapsilat.exception.AuthenticationException;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.elements.ElementsCardSummary;
import com.tapsilat.model.elements.ElementsError;
import com.tapsilat.model.elements.ElementsSession;
import com.tapsilat.model.elements.ElementsSessionCreateRequest;
import com.tapsilat.model.elements.ElementsSessionWithSecret;
import com.tapsilat.service.ElementsService;
import com.tapsilat.unit.utils.MockHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ElementsServiceTest {

    private static final String SESSION_ID = "es_test_8Kq2nV0rX4";
    private static final String CLIENT_SECRET = "es_test_8Kq2nV0rX4_secret_q8Zr0bM1";

    private MockHttpClient httpClient;
    private TapsilatConfig config;
    private ObjectMapper objectMapper;
    private ElementsService elementsService;

    @BeforeEach
    void setUp() {
        config = new TapsilatConfig();
        config.setBaseUrl("https://api.test");
        config.setBearerToken("test-token");

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        httpClient = new MockHttpClient();
        elementsService = new ElementsService(httpClient, config, objectMapper);
    }

    // ==================== createSession ====================

    @Test
    void testCreatePaySession() throws Exception {
        httpClient.setResponse(200, "{"
                + "\"id\":\"es_test_8Kq2nV0rX4\","
                + "\"purpose\":\"pay\","
                + "\"status\":\"requires_payment_method\","
                + "\"order_reference_id\":\"order-ref-1\","
                + "\"attempts\":0,"
                + "\"max_attempts\":3,"
                + "\"created_at\":\"2026-10-22T09:00:00Z\","
                + "\"expires_at\":\"2026-10-22T09:30:00Z\","
                + "\"client_secret\":\"es_test_8Kq2nV0rX4_secret_q8Zr0bM1\","
                + "\"elements_base_url\":\"https://checkout.tapsilat.dev\""
                + "}");

        ElementsSessionCreateRequest request = new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY);
        request.setOrderReferenceId("order-ref-1");
        request.setSaveCard(true);

        ElementsSessionWithSecret session = elementsService.createSession(request);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertEquals("https://api.test/api/v1/elements/sessions", captured.getUri().toString());
        assertEquals("Bearer test-token", captured.getFirstHeader("Authorization").getValue());
        assertEquals(Map.of("purpose", "pay", "order_reference_id", "order-ref-1", "save_card", true),
                bodyOf(captured));

        assertEquals(SESSION_ID, session.getId());
        assertEquals(ElementsConstants.PURPOSE_PAY, session.getPurpose());
        assertEquals(ElementsConstants.STATUS_REQUIRES_PAYMENT_METHOD, session.getStatus());
        assertEquals("order-ref-1", session.getOrderReferenceId());
        assertEquals(0, session.getAttempts());
        assertEquals(3, session.getMaxAttempts());
        assertEquals("2026-10-22T09:00:00Z", session.getCreatedAt());
        assertEquals("2026-10-22T09:30:00Z", session.getExpiresAt());
        assertNull(session.getCompletedAt());
        assertNull(session.getUserId());
        assertNull(session.getCard());
        assertNull(session.getLastError());
        assertEquals(CLIENT_SECRET, session.getClientSecret());
        assertEquals("https://checkout.tapsilat.dev", session.getElementsBaseUrl());
    }

    @Test
    void testCreateTokenizeSession() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"es_test_1\",\"purpose\":\"tokenize\","
                + "\"status\":\"requires_payment_method\",\"user_id\":\"user_123\","
                + "\"client_secret\":\"es_test_1_secret_x\"}");

        ElementsSessionCreateRequest request = new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_TOKENIZE);
        request.setUserId("user_123");
        request.setUserEmail("john@doe.com");
        request.setMode(ElementsConstants.MODE_NON_SECURE);

        ElementsSessionWithSecret session = elementsService.createSession(request);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertEquals(Map.of("purpose", "tokenize", "user_id", "user_123", "user_email", "john@doe.com", "mode", 3),
                bodyOf(captured));

        assertEquals(ElementsConstants.PURPOSE_TOKENIZE, session.getPurpose());
        assertEquals("user_123", session.getUserId());
        assertNull(session.getOrderReferenceId());
        assertEquals("es_test_1_secret_x", session.getClientSecret());
        assertNull(session.getElementsBaseUrl());
    }

    @Test
    void testCreateSessionOmitsUnsetFieldsWithDefaultObjectMapper() throws Exception {
        ElementsService service = new ElementsService(httpClient, config, new ObjectMapper());
        httpClient.setResponse(200, "{\"id\":\"es_test_1\",\"client_secret\":\"es_test_1_secret_x\"}");

        service.createSession(new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY));

        assertEquals("{\"purpose\":\"pay\"}", EntityUtils.toString(httpClient.getCapturedRequest().getEntity()));
    }

    // ==================== getSession ====================

    @Test
    void testGetSessionDecodesCardAndLastError() throws Exception {
        httpClient.setResponse(200, "{"
                + "\"id\":\"es_test_8Kq2nV0rX4\","
                + "\"purpose\":\"pay\","
                + "\"status\":\"failed\","
                + "\"order_reference_id\":\"order-ref-1\","
                + "\"attempts\":3,"
                + "\"max_attempts\":3,"
                + "\"completed_at\":\"2026-10-22T09:12:00Z\","
                + "\"card\":{\"id\":\"card-1\",\"brand\":\"visa\",\"last4\":\"4242\",\"exp_month\":12,\"exp_year\":2030},"
                + "\"last_error\":{\"code\":\"card_declined\",\"message\":\"The card was declined.\",\"retryable\":false}"
                + "}");

        ElementsSession session = elementsService.getSession(SESSION_ID);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertEquals("https://api.test/api/v1/elements/sessions/es_test_8Kq2nV0rX4", captured.getUri().toString());
        assertEquals("Bearer test-token", captured.getFirstHeader("Authorization").getValue());
        assertNull(captured.getEntity());

        assertFalse(session instanceof ElementsSessionWithSecret);
        assertEquals(ElementsConstants.STATUS_FAILED, session.getStatus());
        assertEquals(3, session.getAttempts());
        assertEquals("2026-10-22T09:12:00Z", session.getCompletedAt());

        ElementsCardSummary card = session.getCard();
        assertNotNull(card);
        assertEquals("card-1", card.getId());
        assertEquals(ElementsConstants.BRAND_VISA, card.getBrand());
        assertEquals("4242", card.getLast4());
        assertEquals(12, card.getExpMonth());
        assertEquals(2030, card.getExpYear());

        ElementsError lastError = session.getLastError();
        assertNotNull(lastError);
        assertEquals("card_declined", lastError.getCode());
        assertEquals("The card was declined.", lastError.getMessage());
        assertEquals(Boolean.FALSE, lastError.getRetryable());
    }

    @Test
    void testGetSessionReadsNullCardAndLastError() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"es_mock_1\",\"purpose\":\"pay\","
                + "\"status\":\"requires_payment_method\",\"card\":null,\"last_error\":null}");

        ElementsSession session = elementsService.getSession("es_mock_1");

        assertEquals("es_mock_1", session.getId());
        assertNull(session.getCard());
        assertNull(session.getLastError());
    }

    @Test
    void testGetSessionAcceptsUnknownFieldsAndValues() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"es_live_1\",\"purpose\":\"subscribe\",\"status\":\"on_hold\","
                + "\"new_field\":{\"nested\":[1,2]},"
                + "\"card\":{\"brand\":\"elo\",\"last4\":\"1234\",\"network_token\":true},"
                + "\"last_error\":{\"code\":\"new_code\",\"message\":\"m\",\"details\":{}}}");

        ElementsSession session = elementsService.getSession("es_live_1");

        assertEquals("subscribe", session.getPurpose());
        assertEquals("on_hold", session.getStatus());
        assertEquals("elo", session.getCard().getBrand());
        assertEquals("new_code", session.getLastError().getCode());
        assertNull(session.getLastError().getRetryable());
    }

    // ==================== cancelSession ====================

    @Test
    void testCancelSession() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"es_test_8Kq2nV0rX4\",\"purpose\":\"pay\",\"status\":\"canceled\","
                + "\"attempts\":0,\"max_attempts\":3}");

        ElementsSession session = elementsService.cancelSession(SESSION_ID);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertEquals("https://api.test/api/v1/elements/sessions/es_test_8Kq2nV0rX4/cancel",
                captured.getUri().toString());
        assertEquals("Bearer test-token", captured.getFirstHeader("Authorization").getValue());
        assertNull(captured.getEntity());
        assertEquals(ElementsConstants.STATUS_CANCELED, session.getStatus());
    }

    // ==================== session id ====================

    @Test
    void testSessionIdIsPathEscaped() throws Exception {
        httpClient.setResponse(200, "{\"id\":\"es_test_1\"}");

        elementsService.getSession("es_test_1/../x");
        assertEquals("/api/v1/elements/sessions/es_test_1%2F..%2Fx",
                httpClient.getCapturedRequest().getUri().getRawPath());

        elementsService.cancelSession("es test?x#y");
        assertEquals("/api/v1/elements/sessions/es%20test%3Fx%23y/cancel",
                httpClient.getCapturedRequest().getUri().getRawPath());
        assertNull(httpClient.getCapturedRequest().getUri().getRawQuery());
        assertNull(httpClient.getCapturedRequest().getUri().getRawFragment());
    }

    @Test
    void testEmptySessionIdIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> elementsService.getSession(""));
        assertThrows(IllegalArgumentException.class, () -> elementsService.cancelSession(null));
        assertNull(httpClient.getCapturedRequest());
    }

    // ==================== errors ====================

    @Test
    void testCreateSessionNotEnabled() {
        httpClient.setResponse(403, "{\"error\":{\"code\":\"elements_not_enabled\","
                + "\"message\":\"Elements is not enabled for this organization\"}}");

        ApiException error = assertApiError(
                () -> elementsService.createSession(new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY)),
                403, "elements_not_enabled", "Elements is not enabled for this organization", false);

        assertInstanceOf(AuthenticationException.class, error);
        assertEquals("Authentication failed: [elements_not_enabled] Elements is not enabled for this organization",
                error.getMessage());
    }

    @Test
    void testCreateSessionInvalidRequest() {
        httpClient.setResponse(400, "{\"error\":{\"code\":\"invalid_request\","
                + "\"message\":\"order_reference_id is required\",\"retryable\":false}}");

        ApiException error = assertApiError(
                () -> elementsService.createSession(new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY)),
                400, "invalid_request", "order_reference_id is required", false);

        assertEquals("API request failed: [invalid_request] order_reference_id is required", error.getMessage());
    }

    @Test
    void testGetSessionNotFound() {
        httpClient.setResponse(404, "{\"error\":{\"code\":\"session_not_found\",\"message\":\"session not found\"}}");

        ApiException error = assertApiError(() -> elementsService.getSession("es_test_missing"),
                404, "session_not_found", "session not found", false);

        assertFalse(error instanceof AuthenticationException);
    }

    @Test
    void testCancelSessionUnavailableIsRetryable() {
        httpClient.setResponse(503, "{\"error\":{\"code\":\"unavailable\",\"message\":\"try again\",\"retryable\":true}}");

        ApiException error = assertApiError(() -> elementsService.cancelSession("es_test_1"),
                503, "unavailable", "try again", true);

        assertTrue(error.toString().contains("retryable=true"));
    }

    @Test
    void testUnauthorizedIsApiException() {
        httpClient.setResponse(401, "{\"error\":{\"code\":\"unauthorized\",\"message\":\"invalid token\"}}");

        ApiException error = assertApiError(() -> elementsService.getSession(SESSION_ID),
                401, "unauthorized", "invalid token", false);

        assertInstanceOf(AuthenticationException.class, error);
    }

    @Test
    void testErrorBodiesWithoutNestedObjectKeepTheirMessage() {
        String flat = "{\"code\":101160,\"error\":\"ORDER_ORDER_DETAIL_ORDER_NOT_FOUND\"}";
        httpClient.setResponse(400, flat);
        ApiException flatError = assertThrows(ApiException.class, () -> elementsService.getSession(SESSION_ID));
        assertEquals("API request failed: " + flat, flatError.getMessage());
        assertNull(flatError.getErrorCode());
        assertNull(flatError.getErrorMessage());
        assertFalse(flatError.isRetryable());
        assertEquals(flat, flatError.getResponseBody());

        String html = "<html><body>Bad Gateway</body></html>";
        httpClient.setResponse(502, html);
        ApiException htmlError = assertThrows(ApiException.class, () -> elementsService.getSession(SESSION_ID));
        assertEquals("API request failed: " + html, htmlError.getMessage());
        assertEquals(502, htmlError.getStatusCode());
        assertNull(htmlError.getErrorCode());

        httpClient.setResponse(401, "Unauthorized");
        AuthenticationException authError = assertThrows(AuthenticationException.class,
                () -> elementsService.getSession(SESSION_ID));
        assertEquals("Authentication failed: Unauthorized", authError.getMessage());
        assertNull(authError.getErrorCode());
    }

    // ==================== client secret ====================

    @Test
    void testMalformedCreateResponseDoesNotExposeClientSecret() {
        // A mapper that copies the JSON source into parse errors, as older Jackson versions do by default.
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
        ElementsService service = new ElementsService(httpClient, config, mapper);
        httpClient.setResponse(200, "{\"id\":\"es_test_1\",\"client_secret\":\"" + CLIENT_SECRET + "\","
                + "\"card\":\"not-an-object\"}");

        TapsilatException error = assertThrows(TapsilatException.class,
                () -> service.createSession(new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY)));

        assertNotNull(error.getCause());
        for (Throwable t = error; t != null; t = t.getCause()) {
            assertFalse(String.valueOf(t.getMessage()).contains(CLIENT_SECRET), t.getClass().getName());
            assertFalse(t.toString().contains(CLIENT_SECRET), t.getClass().getName());
        }
    }

    private Map<String, Object> bodyOf(ClassicHttpRequest request) throws Exception {
        assertNotNull(request.getEntity(), "request has no body");
        @SuppressWarnings("unchecked")
        Map<String, Object> body = objectMapper.readValue(EntityUtils.toString(request.getEntity()), Map.class);
        return body;
    }

    private static ApiException assertApiError(Executable call, int statusCode, String code, String message,
            boolean retryable) {
        ApiException error = assertThrows(ApiException.class, call);
        assertEquals(statusCode, error.getStatusCode());
        assertEquals(code, error.getErrorCode());
        assertEquals(message, error.getErrorMessage());
        assertEquals(retryable, error.isRetryable());
        assertTrue(error.getMessage().contains("[" + code + "] " + message), error.getMessage());
        assertNotNull(error.getResponseBody());
        return error;
    }
}
