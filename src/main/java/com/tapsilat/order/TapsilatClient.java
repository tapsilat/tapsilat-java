package com.tapsilat.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.constants.TapsilatConstants;
import com.tapsilat.order.exception.ApiException;
import com.tapsilat.order.exception.AuthenticationException;
import com.tapsilat.order.exception.TapsilatException;
import com.tapsilat.order.model.OrderRequest;
import com.tapsilat.order.model.OrderResponse;
import com.tapsilat.order.validation.OrderRequestValidator;
import com.tapsilat.order.validation.ValidationException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Main client class for interacting with Tapsilat payment API.
 */
public class TapsilatClient implements AutoCloseable {
    
    private static final Logger logger = LoggerFactory.getLogger(TapsilatClient.class);
    
    private final TapsilatConfig config;
    private final ObjectMapper objectMapper;
    private final CloseableHttpClient httpClient;
    
    /**
     * Constructor with configuration.
     * @param config The Tapsilat configuration
     */
    public TapsilatClient(TapsilatConfig config) {
        this.config = Objects.requireNonNull(config, "TapsilatConfig cannot be null");
        this.objectMapper = new ObjectMapper();
        
        // Configure HTTP client with timeouts
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.of(config.getConnectionTimeout(), TimeUnit.MILLISECONDS))
                .setResponseTimeout(Timeout.of(config.getReadTimeout(), TimeUnit.MILLISECONDS))
                .build();
        
        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
    
    /**
     * Create a new payment order.
     * @param orderRequest The order request
     * @return OrderResponse containing order details
     * @throws TapsilatException if the API call fails
     */
    public OrderResponse createOrder(OrderRequest orderRequest) throws TapsilatException {
        try {
            // Validate request using validator utility
            OrderRequestValidator.validateOrThrow(orderRequest);
            
            // Prepare HTTP request
            HttpPost httpPost = buildAuthorizedJsonPost(TapsilatConstants.ENDPOINT_CREATE_ORDER, orderRequest);
            
            return executeRequest(httpPost, OrderResponse.class);
            
        } catch (ValidationException e) {
            // Wrap validation errors in TapsilatException for consistent exception handling
            throw new TapsilatException(e.getMessage(), e);
        } catch (IOException | ParseException e) {
            logger.error("Error creating order", e);
            throw new TapsilatException("Failed to create order: " + e.getMessage(), e);
        }
    }
    
    /**
     * Close the HTTP client.
     */
    @Override
    public void close() {
        try {
            if (httpClient != null) {
                httpClient.close();
            }
        } catch (IOException e) {
            logger.error("Error closing HTTP client", e);
        }
    }

    private HttpPost buildAuthorizedJsonPost(String endpoint, Object payload) throws IOException, TapsilatException {
        String url = resolveEndpoint(endpoint);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(TapsilatConstants.HEADER_CONTENT_TYPE, TapsilatConstants.MEDIA_TYPE_JSON);
        httpPost.setHeader(TapsilatConstants.HEADER_ACCEPT, TapsilatConstants.MEDIA_TYPE_JSON);

        String bearerToken = config.getBearerToken();
        if (bearerToken == null || bearerToken.trim().isEmpty()) {
            throw new AuthenticationException("Bearer token must be provided in TapsilatConfig");
        }

        httpPost.setHeader(TapsilatConstants.HEADER_AUTHORIZATION, "Bearer " + bearerToken);

        String requestBody = objectMapper.writeValueAsString(payload);
        httpPost.setEntity(new StringEntity(requestBody, ContentType.APPLICATION_JSON));

        logger.debug("Sending POST {} with body: {}", url, requestBody);
        return httpPost;
    }

    private <T> T executeRequest(HttpPost request, Class<T> responseType) throws IOException, ParseException, TapsilatException {
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String responseBody = response.getEntity() != null
                    ? EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8)
                    : "";

            int statusCode = response.getCode();
            logger.debug("Response status: {}", statusCode);
            logger.debug("Response body: {}", responseBody);

            // Handle authentication errors
            if (statusCode == TapsilatConstants.HTTP_UNAUTHORIZED || statusCode == TapsilatConstants.HTTP_FORBIDDEN) {
                throw new AuthenticationException(
                        "Authentication failed with status " + statusCode + ": " + responseBody,
                        statusCode
                );
            }

            // Handle other API errors
            if (statusCode >= 400) {
                throw new ApiException(
                        "API request failed with status " + statusCode,
                        statusCode,
                        null,
                        responseBody
                );
            }

            if (responseBody.isEmpty()) {
                return null;
            }

            return objectMapper.readValue(responseBody, responseType);
        }
    }

    private String resolveEndpoint(String endpoint) {
        String baseUrl = config.getBaseUrl();
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            throw new IllegalStateException("Base URL must be provided in TapsilatConfig");
        }

        boolean baseEndsWithSlash = baseUrl.endsWith("/");
        boolean endpointStartsWithSlash = endpoint.startsWith("/");

        if (baseEndsWithSlash && endpointStartsWithSlash) {
            return baseUrl + endpoint.substring(1);
        } else if (!baseEndsWithSlash && !endpointStartsWithSlash) {
            return baseUrl + '/' + endpoint;
        }
        return baseUrl + endpoint;
    }
} 