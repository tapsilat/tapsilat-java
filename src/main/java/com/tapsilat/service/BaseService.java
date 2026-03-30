package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.ApiException;
import com.tapsilat.exception.AuthenticationException;
import com.tapsilat.exception.TapsilatException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntityContainer;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.client5.http.classic.methods.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class BaseService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final CloseableHttpClient httpClient;
    protected final TapsilatConfig config;
    protected final ObjectMapper objectMapper;

    protected BaseService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.config = config;
        this.objectMapper = objectMapper;
    }

    protected <T> T executeRequest(HttpUriRequestBase request, Class<T> responseType)
            throws IOException, ParseException, TapsilatException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = response.getEntity() != null
                    ? EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)
                    : "";

            int statusCode = response.getCode();
            logger.debug("Response status: {}", statusCode);

            if (statusCode == TapsilatConstants.HTTP_UNAUTHORIZED || statusCode == TapsilatConstants.HTTP_FORBIDDEN) {
                throw new AuthenticationException("Authentication failed: " + responseBody, statusCode);
            }

            if (statusCode >= 400) {
                throw new ApiException("API request failed: " + responseBody, statusCode, null, responseBody);
            }

            if (responseBody.isEmpty() || responseType == null)
                return null;
            if (responseType == String.class)
                return (T) responseBody;

            return objectMapper.readValue(responseBody, responseType);
        }
    }

    protected HttpUriRequestBase buildRequest(String method, String endpoint, Object payload,
            Map<String, String> params) throws IOException, TapsilatException {
        String url = resolveEndpoint(endpoint);
        if (params != null && !params.isEmpty()) {
            StringBuilder sb = new StringBuilder(url).append("?");
            params.forEach((k, v) -> {
                if (v != null)
                    sb.append(k).append("=").append(v).append("&");
            });
            url = sb.toString();
        }

        HttpUriRequestBase request;
        switch (method.toUpperCase()) {
            case "POST":
                request = new HttpPost(url);
                break;
            case "GET":
                request = new HttpGet(url);
                break;
            case "PUT":
                request = new HttpPut(url);
                break;
            case "DELETE":
                request = new HttpDelete(url);
                break;
            case "PATCH":
                request = new HttpPatch(url);
                break;
            default:
                throw new IllegalArgumentException("Unsupported method: " + method);
        }

        request.setHeader(TapsilatConstants.HEADER_CONTENT_TYPE, TapsilatConstants.MEDIA_TYPE_JSON);
        request.setHeader(TapsilatConstants.HEADER_ACCEPT, TapsilatConstants.MEDIA_TYPE_JSON);

        String token = config.getBearerToken();
        if (token != null && !token.isEmpty()) {
            request.setHeader(TapsilatConstants.HEADER_AUTHORIZATION, "Bearer " + token);
        }

        if (payload != null && request instanceof HttpEntityContainer) {
            String json = objectMapper.writeValueAsString(payload);
            ((HttpEntityContainer) request).setEntity(
                    new StringEntity(json, ContentType.APPLICATION_JSON));
        }

        return request;
    }

    public String healthCheck() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_HEALTH, null, null), String.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Health check failed", e);
        }
    }

    private String resolveEndpoint(String endpoint) {
        String baseUrl = config.getBaseUrl();
        // If endpoint already contains basic path prefix, don't re-append it
        if (endpoint.startsWith("/api/v1")) {
            // Remove /api/v1 from baseURL if it's already there to avoid duplication
            String base = baseUrl;
            if (base.endsWith("/api/v1")) {
                base = base.substring(0, base.length() - 7);
            } else if (base.endsWith("/api/v1/")) {
                base = base.substring(0, base.length() - 8);
            }
            
            if (base.endsWith("/") && endpoint.startsWith("/"))
                return base + endpoint.substring(1);
            if (!base.endsWith("/") && !endpoint.startsWith("/"))
                return base + "/" + endpoint;
            return base + endpoint;
        }

        if (baseUrl.endsWith("/") && endpoint.startsWith("/"))
            return baseUrl + endpoint.substring(1);
        if (!baseUrl.endsWith("/") && !endpoint.startsWith("/"))
            return baseUrl + "/" + endpoint;
        return baseUrl + endpoint;
    }
}
