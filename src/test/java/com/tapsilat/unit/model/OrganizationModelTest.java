package com.tapsilat.unit.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.model.organization.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationModelTest {
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
    }

    @Test
    void testCallbackURLDTOSerialization() throws JsonProcessingException {
        CallbackURLDTO dto = new CallbackURLDTO();
        dto.setCallbackUrl("http://success.url");
        dto.setFailCallbackUrl("http://fail.url");

        String json = mapper.writeValueAsString(dto);
        JsonNode node = mapper.readTree(json);

        assertEquals("http://success.url", node.get("callback_url").asText());
        assertEquals("http://fail.url", node.get("fail_callback_url").asText());
    }

    @Test
    void testSetLimitUserRequestSerialization() throws JsonProcessingException {
        SetLimitUserRequest request = new SetLimitUserRequest();
        request.setUserId("user-1");
        request.setLimitId("limit-1");

        String json = mapper.writeValueAsString(request);
        JsonNode node = mapper.readTree(json);

        assertEquals("user-1", node.get("user_id").asText());
        assertEquals("limit-1", node.get("limit_id").asText());
    }
}
