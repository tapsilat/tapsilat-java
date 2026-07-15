package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.submerchant.SubmerchantCreateRequest;
import com.tapsilat.model.submerchant.SubmerchantUpdateRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hc.core5.http.ParseException;

public class SubmerchantService extends BaseService {
    public SubmerchantService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    public Map<String, Object> create(SubmerchantCreateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", "/submerchants", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create submerchant", e);
        }
    }

    public Map<String, Object> get(String id) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/submerchants/" + id, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get submerchant", e);
        }
    }

    public Map<String, Object> update(String id, SubmerchantUpdateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("PATCH", "/submerchants/" + id, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to update submerchant", e);
        }
    }

    public Map<String, Object> delete(String id) throws TapsilatException {
        try {
            return executeRequest(buildRequest("DELETE", "/submerchants/" + id, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to delete submerchant", e);
        }
    }

    public Map<String, Object> list(Integer page, Integer perPage) throws TapsilatException {
        try {
            Map<String, String> query = new HashMap<>();
            if (page != null) query.put("page", String.valueOf(page));
            if (perPage != null) query.put("per_page", String.valueOf(perPage));
            return executeRequest(buildRequest("GET", "/submerchants", null, query), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to list submerchants", e);
        }
    }
}
