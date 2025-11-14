package com.tapsilat.order;

import com.tapsilat.order.builder.OrderRequestBuilder;
import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;
import com.tapsilat.order.exception.TapsilatException;
import com.tapsilat.order.model.OrderRequest;
import com.tapsilat.order.model.OrderResponse;

import java.math.BigDecimal;

/**
 * Simple manual example that calls the real Tapsilat API and
 * prints orderId and referenceId from the response.
 *
 * WARNING: This will create a real order when run. Configure your
 * bearer token and base URL before executing.
 */
public class TapsilatClientLiveExample {

    public static void main(String[] args) {
        // Configure these before running
        String baseUrl = System.getenv().getOrDefault("TAPSILAT_BASE_URL", "https://panel.tapsilat.dev");
        String bearerToken = System.getenv("TAPSILAT_BEARER_TOKEN");

        if (bearerToken == null || bearerToken.isEmpty()) {
            System.err.println("TAPSILAT_BEARER_TOKEN env var must be set before running this example.");
            return;
        }

        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl(baseUrl);
        config.setBearerToken(bearerToken);

        TapsilatClient client = new TapsilatClient(config);

        try {
            OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                    .amount(new BigDecimal("10.00"))
                    .currency(Currency.TRY)
                    .locale(Locale.TR)
                    .buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111")
                    .description("Live API test order")
                    .callbackUrl("https://example.com/payment-complete")
                    .conversationId("order-" + System.currentTimeMillis())
                    .build();

            OrderResponse response = client.createOrder(orderRequest);

            if (response != null) {
                System.out.println("Order ID:     " + response.getOrderId());
                System.out.println("Reference ID: " + response.getReferenceId());
            } else {
                System.out.println("No data object in response: " + response);
            }
        } catch (TapsilatException e) {
            System.err.println("Error calling Tapsilat API: " + e.getMessage());
            if (e.getStatusCode() > 0) {
                System.err.println("HTTP Status: " + e.getStatusCode());
            }
        } finally {
            client.close();
        }
    }
}
