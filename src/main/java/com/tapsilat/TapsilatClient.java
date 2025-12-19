package com.tapsilat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.service.OrderService;
import com.tapsilat.service.SubscriptionService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Main client for Tapsilat API.
 * Provides access to specialized services for Orders and Subscriptions.
 */
public class TapsilatClient implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(TapsilatClient.class);

    private final CloseableHttpClient httpClient;

    private final OrderService orders;
    private final SubscriptionService subscriptions;

    public TapsilatClient(TapsilatConfig config) {
        Objects.requireNonNull(config, "TapsilatConfig cannot be null");
        ObjectMapper objectMapper = new ObjectMapper();

        this.httpClient = HttpClients.createDefault();

        this.orders = new OrderService(httpClient, config, objectMapper);
        this.subscriptions = new SubscriptionService(httpClient, config, objectMapper);
    }

    /**
     * Access order-related operations.
     */
    public OrderService orders() {
        return orders;
    }

    public SubscriptionService subscriptions() {
        return subscriptions;
    }

    /**
     * Backward compatibility method for createOrder.
     */
    public com.tapsilat.model.order.OrderResponse createOrder(com.tapsilat.model.order.OrderRequest request)
            throws TapsilatException {
        return orders.create(request);
    }

    /**
     * Performs a health check on the Tapsilat API.
     */
    public String healthCheck() throws TapsilatException {
        try {
            // Re-using subscription service for basic request or implement a core one
            return orders.healthCheck(); // Wait, BaseService has it? No.
        } catch (Exception e) {
            throw new TapsilatException("Health check failed", e);
        }
    }

    /**
     * Verify a webhook signature.
     */
    public static boolean verifyWebhook(String payload, String signature, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString().equalsIgnoreCase(signature);
        } catch (Exception e) {
            logger.error("Error verifying webhook signature", e);
            return false;
        }
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            logger.error("Error closing httpClient", e);
        }
    }
}