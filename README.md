# Tapsilat Java SDK

A lightweight Java SDK for integrating with the Tapsilat payment API. This SDK provides a simple and intuitive way to create payment orders without any framework dependencies.

## Features

- ✅ **Framework-free**: No Spring, Quarkus, or other framework dependencies
- ✅ **Lightweight**: Minimal dependencies (HTTP client, JSON processing, logging)
- ✅ **Type-safe**: Full Java type safety with enums and validation
- ✅ **Builder pattern**: Easy-to-use builder for creating order requests
- ✅ **Comprehensive validation**: Built-in request validation
- ✅ **Error handling**: Custom exception handling with HTTP status codes
- ✅ **Logging**: SLF4J logging support
- ✅ **Thread-safe**: Safe for concurrent usage

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.tapsilat</groupId>
    <artifactId>tapsilat-java-client</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Gradle

Add the following dependency to your `build.gradle`:

```gradle
implementation 'com.tapsilat:tapsilat-java-client:1.0.0-SNAPSHOT'
```

## Quick Start

### Basic Usage

```java
import com.tapsilat.order.TapsilatClient;
import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.model.OrderRequest;
import com.tapsilat.order.model.Buyer;
import java.math.BigDecimal;

// Create client using bearer token
TapsilatConfig config = new TapsilatConfig();
config.setBaseUrl(System.getenv().getOrDefault("TAPSILAT_BASE_URL", "https://panel.tapsilat.dev"));
config.setBearerToken(System.getenv("TAPSILAT_BEARER_TOKEN"));

try (TapsilatClient client = new TapsilatClient(config)) {
    // Create buyer
    Buyer buyer = new Buyer("John", "Doe", "john.doe@example.com");

    // Create order request
    OrderRequest orderRequest = new OrderRequest();
    orderRequest.setAmount(new BigDecimal("150.75"));
    orderRequest.setCurrency("TRY");
    orderRequest.setLocale("tr");
    orderRequest.setBuyer(buyer);
    orderRequest.setDescription("Premium Subscription");
    orderRequest.setCallbackUrl("https://your-website.com/payment-complete");
    orderRequest.setConversationId("order-" + System.currentTimeMillis());

    // Create order
    OrderResponse response = client.createOrder(orderRequest);
    System.out.println("Payment URL: " + response.getData().getPaymentUrl());
    System.out.println("Reference ID: " + response.getData().getReferenceId());
}
```

### Using Builder Pattern

```java
import com.tapsilat.order.builder.OrderRequestBuilder;
import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;

OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
    .amount(150.75)
    .currency(Currency.TRY)
    .locale(Locale.TR)
    .buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111")
    .description("Premium Subscription - Annual Plan")
    .callbackUrl("https://your-website.com/payment-complete")
    .conversationId("order-" + System.currentTimeMillis())
    .metadata("productId", "PREMIUM-12M")
    .metadata("customerType", "new")
    .build();

OrderResponse response = client.createOrder(orderRequest);
```

## API Reference

### TapsilatClient

The main client class for interacting with the Tapsilat API.

#### Constructors

```java
// With configuration (TapsilatClient implements AutoCloseable)
TapsilatConfig config = new TapsilatConfig();
config.setBaseUrl("https://panel.tapsilat.dev");
config.setBearerToken("your-bearer-token");

try (TapsilatClient client = new TapsilatClient(config)) {
    // use client
}
```

#### Methods

```java
// Create order
OrderResponse createOrder(OrderRequest orderRequest) throws TapsilatException

// Close client
void close()
```

### OrderRequest

Represents a payment order request.

#### Required Fields

- `amount` (BigDecimal): Payment amount with up to 2 decimal places
- `currency` (String): Supported currencies: TRY, USD, EUR, GBP
- `locale` (String): Interface language: tr (Turkish) or en (English)
- `buyer` (Buyer): Customer information

#### Optional Fields

- `description` (String): Order description
- `callbackUrl` (String): Redirect URL after payment
- `conversationId` (String): Custom reference ID
- `metadata` (List<Metadata>): Key-value pairs for reference

### Buyer

Customer information for the order.

#### Required Fields

- `name` (String): Customer first name
- `surname` (String): Customer last name
- `email` (String): Customer email address

#### Optional Fields

- `phone` (String): Customer phone number
- `identityNumber` (String): National ID or tax number

### Metadata

Key-value pairs for order reference.

```java
Metadata metadata = new Metadata("productId", "PREMIUM-12M");
```

### Enums

#### Currency

```java
Currency.TRY  // Turkish Lira
Currency.USD  // US Dollar
Currency.EUR  // Euro
Currency.GBP  // British Pound
```

#### Locale

```java
Locale.TR  // Turkish
Locale.EN  // English
```

### Configuration

```java
TapsilatConfig config = new TapsilatConfig(
    "https://panel.tapsilat.dev",  // Base URL (optional)
    30000,                        // Connection timeout in ms (optional)
    60000                         // Read timeout in ms (optional)
);
config.setBearerToken("your-bearer-token");

// Optional: override defaults via environment variable before starting the JVM
// export TAPSILAT_BASE_URL="https://panel.tapsilat.dev"
```

## Error Handling

The SDK throws `TapsilatException` for all API errors:

```java
try {
    OrderResponse response = client.createOrder(orderRequest);
} catch (TapsilatException e) {
    System.err.println("Error: " + e.getMessage());
    System.err.println("Status Code: " + e.getStatusCode());
}
```

## Examples

### Complete Example with Metadata

```java
import com.tapsilat.order.*;
import com.tapsilat.order.builder.OrderRequestBuilder;
import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;
import com.tapsilat.order.model.Metadata;

public class PaymentExample {
    public static void main(String[] args) {
        try {
            // Create client using bearer token
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");

            try (TapsilatClient client = new TapsilatClient(config)) {
            
            // Create order with metadata
            OrderRequest orderRequest = OrderRequestBuilder.newBuilder()
                .amount(150.75)
                .currency(Currency.TRY)
                .locale(Locale.TR)
                .buyer("John", "Doe", "john.doe@example.com", "+9099999999", "11111111111")
                .description("Premium Subscription - Annual Plan")
                .callbackUrl("https://your-website.com/payment-complete")
                .conversationId("order-" + System.currentTimeMillis())
                .metadata("productId", "PREMIUM-12M")
                .metadata("customerType", "new")
                .metadata("campaign", "summer2025")
                .build();
            
            // Create order
            OrderResponse response = client.createOrder(orderRequest);
            
            if ("success".equals(response.getStatus())) {
                System.out.println("Order created successfully!");
                System.out.println("Order ID: " + response.getData().getOrderId());
                System.out.println("Reference ID: " + response.getData().getReferenceId());
                System.out.println("Payment URL: " + response.getData().getPaymentUrl());
                System.out.println("Checkout URL: " + response.getData().getCheckoutUrl());
                
                // Redirect user to checkout URL
                // redirectUser(response.getData().getCheckoutUrl());
            } else {
                System.err.println("Failed to create order: " + response.getMessage());
            }
            }
            
        } catch (TapsilatException e) {
            System.err.println("Error creating order: " + e.getMessage());
            if (e.getStatusCode() > 0) {
                System.err.println("HTTP Status: " + e.getStatusCode());
            }
        }
    }
}
```

## Response Format

The API returns responses in the following format:

```json
{
  "status": "success",
  "message": "Order created successfully",
  "data": {
    "orderId": "f81d4fae-7dec-11d0-a765-00a0c91e6bf6",
    "reference_id": "03539571-9fab-4f75-82f1-677696d79ef4",
    "paymentUrl": "https://payment.tapsilat.com/pay/order_123456789",
    "checkout_url": "https://checkout.tapsilat.com/?reference_id=order_123456789",
    "conversationId": "order-1703123456789",
    "amount": "150.75",
    "currency": "TRY"
  }
}
```

## Logging

The SDK uses SLF4J for logging. Configure your logging framework to see debug information:

```xml
<!-- Logback configuration -->
<logger name="com.tapsilat.order" level="DEBUG"/>
```

## Requirements

- Java 17 or higher
- Maven 3.6+ or Gradle 7+

## Dependencies

- Apache HttpClient 5.3
- Jackson Databind 2.16.1
- SLF4J API 2.0.9
- JUnit Jupiter 5.10.1 (test)
- Mockito 5.8.0 (test)

## License

This project is licensed under the MIT License.

## Support

For support and questions, please contact the Tapsilat team or create an issue in this repository.

## Live API Example

To run a real API call locally and inspect `orderId`/`referenceId`, export your credentials and run the manual example:

```zsh
export TAPSILAT_BEARER_TOKEN="<your-token>"
export TAPSILAT_BASE_URL="https://panel.tapsilat.dev" # optional
mvn -Dtest=TapsilatClientLiveExample test
```

The example will create a live order, print the IDs, and close the client automatically.
