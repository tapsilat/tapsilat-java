package com.tapsilat.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.submerchant.SubmerchantCreateRequest;
import com.tapsilat.model.submerchant.SubmerchantUpdateRequest;
import com.tapsilat.service.SubmerchantService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubmerchantServiceTest {
    private CloseableHttpClient httpClient;
    private TapsilatConfig config;
    private ObjectMapper objectMapper;
    private SubmerchantService service;

    @BeforeEach
    public void setup() {
        httpClient = mock(CloseableHttpClient.class);
        config = mock(TapsilatConfig.class);
        when(config.getBaseUrl()).thenReturn("https://api.tapsilat.com");
        when(config.getBearerToken()).thenReturn("test-key");

        objectMapper = new ObjectMapper();
        service = new SubmerchantService(httpClient, config, objectMapper);
    }

    private void mockResponse(int statusCode, String jsonResponse) throws IOException {
        CloseableHttpResponse response = mock(CloseableHttpResponse.class);
        when(response.getCode()).thenReturn(statusCode);
        if (jsonResponse != null) {
            when(response.getEntity()).thenReturn(new StringEntity(jsonResponse));
        }
        when(httpClient.execute(any(ClassicHttpRequest.class))).thenReturn(response);
    }

    @Test
    public void testCreateSubmerchant() throws Exception {
        mockResponse(200, "{\"status\":\"success\",\"data\":{\"id\":\"sub_123\"}}");
        
        SubmerchantCreateRequest req = new SubmerchantCreateRequest();
        req.setName("Test");
        
        Map<String, Object> resp = service.create(req);
        assertNotNull(resp);
        assertEquals("success", resp.get("status"));
    }

    @Test
    public void testGetSubmerchant() throws Exception {
        mockResponse(200, "{\"status\":\"success\",\"data\":{\"id\":\"sub_123\"}}");
        
        Map<String, Object> resp = service.get("sub_123");
        assertNotNull(resp);
        assertEquals("success", resp.get("status"));
    }
}
