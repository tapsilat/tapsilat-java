package com.tapsilat.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.organization.*;
import com.tapsilat.service.OrganizationService;
import com.tapsilat.unit.utils.MockHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationServiceTest {

    private MockHttpClient httpClient;
    private OrganizationService organizationService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://api.test");
        config.setBearerToken("test-token");

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
        httpClient = new MockHttpClient();
        organizationService = new OrganizationService(httpClient, config, objectMapper);
    }

    // ==================== getCallback ====================

    @Test
    void testGetCallbackSuccess() throws Exception {
        httpClient.setResponse(200, "{\"callback_url\":\"http://success.url\"}");

        Map<String, Object> response = organizationService.getCallback();

        assertNotNull(response);
        assertEquals("http://success.url", response.get("callback_url"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/callback"));
    }

    // ==================== updateCallback ====================

    @Test
    void testUpdateCallbackSuccess() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        CallbackURLDTO request = new CallbackURLDTO();
        request.setCallbackUrl("http://newsuccess.test");
        Map<String, Object> response = organizationService.updateCallback(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("PATCH", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/callback"));
    }

    // ==================== createBusiness ====================

    @Test
    void testCreateBusinessSuccess() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrgCreateBusinessRequest request = new OrgCreateBusinessRequest();
        request.setAddress("Test Address");
        request.setBusinessName("Test Business");
        request.setBusinessType(0);
        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhone("+905551234567");
        request.setIdentityNumber("12345678901");
        request.setTaxNumber("1234567890");
        request.setTaxOffice("Test Office");
        request.setZipCode("34000");

        Map<String, Object> response = organizationService.createBusiness(request);

        assertNotNull(response);
        assertTrue((Boolean) response.get("success"));

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/business/create"));
    }

    // ==================== getCurrencies ====================

    @Test
    void testGetCurrencies() throws Exception {
        httpClient.setResponse(200, "{\"currencies\":[]}");

        Map<String, Object> response = organizationService.getCurrencies();

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/currencies"));
    }

    // ==================== getLimitUser ====================

    @Test
    void testGetLimitUser() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = organizationService.getLimitUser("user-123");

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/limit/user"));
        assertTrue(captured.getUri().toString().contains("user_id=user-123"));
    }

    // ==================== setLimitUser ====================

    @Test
    void testSetLimitUser() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        SetLimitUserRequest request = new SetLimitUserRequest("user-123", "limit-1");
        Map<String, Object> response = organizationService.setLimitUser(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/limit/user"));
    }

    // ==================== getLimits ====================

    @Test
    void testGetLimits() throws Exception {
        httpClient.setResponse(200, "{\"limits\":[]}");

        Map<String, Object> response = organizationService.getLimits();

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/limits"));
    }

    // ==================== listVpos ====================

    @Test
    void testListVpos() throws Exception {
        httpClient.setResponse(200, "{\"vpos\":[]}");

        GetVposRequest request = new GetVposRequest("TRY");
        Map<String, Object> response = organizationService.listVpos(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/list-vpos"));
    }

    // ==================== getMeta ====================

    @Test
    void testGetMeta() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = organizationService.getMeta("meta_name");

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/metas/meta_name"));
    }

    // ==================== getScopes ====================

    @Test
    void testGetScopes() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = organizationService.getScopes();

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/scopes"));
    }

    // ==================== getSuborganizations ====================

    @Test
    void testGetSuborganizations() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        Map<String, Object> response = organizationService.getSuborganizations(1, 10);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("GET", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/suborganizations"));
        assertTrue(captured.getUri().toString().contains("page=1"));
        assertTrue(captured.getUri().toString().contains("per_page=10"));
    }

    // ==================== createUser ====================

    @Test
    void testCreateUser() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrgCreateUserRequest request = new OrgCreateUserRequest();
        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPhone("+905551234567");
        request.setConversationId("conv-1");
        request.setIdentityNumber("12345678901");
        request.setIsMailVerified(true);
        request.setReferenceId("ref-1");

        Map<String, Object> response = organizationService.createUser(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/user/create"));
    }

    // ==================== verifyUser ====================

    @Test
    void testVerifyUser() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrgUserVerifyRequest request = new OrgUserVerifyRequest("user-123");
        Map<String, Object> response = organizationService.verifyUser(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/user/verify"));
    }

    // ==================== verifyUserMobile ====================

    @Test
    void testVerifyUserMobile() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        OrgUserMobileVerifyRequest request = new OrgUserMobileVerifyRequest("user-123");
        Map<String, Object> response = organizationService.verifyUserMobile(request);

        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/user/verify-mobile"));
    }

    @Test
    void testCreateUserToken() throws Exception {
        httpClient.setResponse(200, "{\"success\":true}");

        com.tapsilat.model.organization.OrgUserTokenRequest req = new com.tapsilat.model.organization.OrgUserTokenRequest();
        req.setEmail("test@test.com");

        Map<String, Object> response = organizationService.createUserToken(req);
        assertNotNull(response);

        ClassicHttpRequest captured = httpClient.getCapturedRequest();
        assertEquals("POST", captured.getMethod());
        assertTrue(captured.getUri().toString().contains("/organization/user/token"));
    }
}
