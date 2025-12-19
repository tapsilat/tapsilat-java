# Tapsilat Java SDK

A lightweight Java SDK for integrating with the Tapsilat payment API. This SDK provides a simple and intuitive way to manage orders, subscriptions, and payment terms without any complex third-party dependencies.

## Features

- ✅ **Framework-agnostic**: No Spring, Quarkus, or other framework locking
- ✅ **Type-safe Models**: Java model representation for all API entities
- ✅ **Service-Oriented**: Logical separation for Orders, Subscriptions, and Terms
- ✅ **Builder Pattern**: Fluent builder for creating complex order requests
- ✅ **Automated Validation**: Built-in request validation before sending to API
- ✅ **Webhook Support**: Helper methods for signature verification

## Installation

### Maven

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.tapsilat</groupId>
    <artifactId>tapsilat-java-client</artifactId>
    <version>2025.12.19.1</version>
</dependency>
```

### Gradle

Add the dependency to your `build.gradle`:

```gradle
implementation 'com.tapsilat:tapsilat-java-client:2025.12.19.1'
```

---

## Quick Start

### Basic Configuration

```java
import com.tapsilat.TapsilatClient;
import com.tapsilat.config.TapsilatConfig;

TapsilatConfig config = new TapsilatConfig();
config.setBearerToken("YOUR_API_KEY");
// Optional: config.setBaseUrl("https://panel.tapsilat.dev");

TapsilatClient client = new TapsilatClient(config);
```

---

## Order Management

### Create an Order (using Builder)

```java
import com.tapsilat.builder.OrderRequestBuilder;
import com.tapsilat.model.common.Buyer;
import com.tapsilat.model.order.OrderResponse;
import java.math.BigDecimal;

Buyer buyer = new Buyer("John", "Doe", "john@example.com");
buyer.setCity("Istanbul");

OrderResponse response = client.orders().create(
    OrderRequestBuilder.newBuilder()
        .amount(new BigDecimal("150.00"))
        .currency("TRY")
        .locale("tr")
        .buyer(buyer)
        .description("Order #12345")
        .paymentSuccessUrl("https://example.com/success")
        .paymentFailureUrl("https://example.com/fail")
        .build()
);

System.out.println("Checkout URL: " + response.getCheckoutUrl());
```

### List & Filter Orders

```java
Map<String, Object> orderList = client.orders().list(
    1,          // page
    10,         // per_page
    null,       // start_date
    null,       // end_date
    null,       // organization_id
    null,       // reference_id
    null        // buyer_id
);
```

---

## Subscription Management

### Create Subscription

```java
import com.tapsilat.model.subscription.SubscriptionCreateRequest;
import com.tapsilat.model.subscription.SubscriptionUser;

SubscriptionCreateRequest request = new SubscriptionCreateRequest();
request.setTitle("Premium Plan");
request.setAmount(new BigDecimal("29.90"));
request.setPeriod(1); // Monthly

SubscriptionUser user = new SubscriptionUser();
user.setEmail("subscriber@example.com");
request.setUser(user);

client.subscriptions().create(request);
```

---

## Payment Terms (Taksit/Vade)

The SDK provides methods to manage payment terms (installments).

```java
import com.tapsilat.model.order.OrderPaymentTermCreateRequest;

OrderPaymentTermCreateRequest term = new OrderPaymentTermCreateRequest();
term.setOrderId("ORDER_ID");
term.setAmount(new BigDecimal("50.00"));
term.setDueDate("2025-01-01");

client.orders().createTerm(term);
```

---

## Webhook Verification

Tapsilat sends signed webhooks. You can verify them securely:

```java
boolean isValid = TapsilatClient.verifyWebhook(
    payloadBody, 
    receivedSignature, 
    "YOUR_WEBHOOK_SECRET"
);
```

## Advanced Options

### Error Handling

```java
try {
    client.orders().create(request);
} catch (TapsilatException e) {
    System.err.println("API Error: " + e.getMessage());
}
```

### Logging

The SDK uses **SLF4J**. To see detailed logs, configure your `logback.xml` or `log4j2.xml`:
```xml
<logger name="com.tapsilat" level="DEBUG"/>
```

---

## Requirements
- **Java 17** or higher
- **Apache HttpClient 5**
- **Jackson 2.x**

---

## License
MIT License.
