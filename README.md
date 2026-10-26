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

## System Operations

You can retrieve global system enumerations using the `SystemService`:

```java
// Get available error codes
Map<String, Object> errorCodes = client.system().getErrorCodes();

// Get order statuses
Map<String, Object> orderStatuses = client.system().getOrderStatuses();

// Get transaction payment types
Map<String, Object> paymentTypes = client.system().getTransactionPaymentTypes();
```

---

## Tapsilat Elements Sessions

Tapsilat Elements is the native Android and iOS card sheet. Your backend creates a session with its secret API key and passes only the session's `client_secret` to the app, which opens the sheet with it. Card data goes from the sheet to Tapsilat encrypted and never reaches your backend.

```java
import com.tapsilat.constants.ElementsConstants;
import com.tapsilat.model.elements.ElementsSession;
import com.tapsilat.model.elements.ElementsSessionCreateRequest;
import com.tapsilat.model.elements.ElementsSessionWithSecret;

ElementsSessionCreateRequest request = new ElementsSessionCreateRequest(ElementsConstants.PURPOSE_PAY);
request.setOrderReferenceId(order.getReferenceId());
request.setSaveCard(true);

ElementsSessionWithSecret session = client.elements().createSession(request);
// Return session.getId() and session.getClientSecret() to your app. Do not log or store the secret.

// Later, read the outcome on your backend instead of trusting the app:
ElementsSession outcome = client.elements().getSession(session.getId());
if (ElementsConstants.STATUS_SUCCEEDED.equals(outcome.getStatus())) {
    // fulfil the order; outcome.getCard() holds brand, last4 and expiry
}
```

A `pay` session needs `orderReferenceId`. A `tokenize` session saves a card for `userId`, with `mode` set to `ElementsConstants.MODE_SECURE` (3D Secure), `MODE_HALF_SECURE` or `MODE_NON_SECURE`. `client.elements().cancelSession(id)` cancels a session that is still waiting for a card; a session that is confirming or has completed comes back unchanged.

The client secret is returned only by `createSession`. `ElementsSessionWithSecret.toString()` prints it as `[redacted]`, and JSON serialization keeps it, so you can still send it to the app. Status, purpose and card brand are plain strings, so values added to the API later still decode; `ElementsConstants` lists the known ones.

Elements errors carry a stable code (for example `elements_not_enabled`, `session_not_found`) and a retryable flag, read with `ApiException.getErrorCode()` and `isRetryable()` (see [Error Handling](#error-handling)).

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


## 📚 API Reference

### Core Operations (OrderService)

| Method                  | Description          |
| ----------------------- | -------------------- |
| `create()`              | Create payment order |
| `get()`                 | Get order details    |
| `getStatus()`           | Check payment status |
| `refund()`              | Process partial refund |
| `refundAll()`           | Process full refund  |
| `refundRequest()`       | Create refund request |
| `cancel()`              | Cancel order         |
| `getCheckoutUrl()`      | Get checkout URL     |
| `getPayments()`         | Get order payments   |
| `pdf()`                 | Export order as PDF  |
| `excel()`               | Export order as Excel|
| `addOip()`              | Add item payment     |
| `terminate()`           | Terminate order      |

### Payment Terms

| Method                   | Description         |
| ------------------------ | ------------------- |
| `getTerm()`              | Get payment terms   |
| `createTerm()`           | Create payment term |
| `updateTerm()`           | Update term         |
| `deleteTerm()`           | Delete term         |
| `refundTerm()`           | Refund payment term |

### OrganizationService

| Method                          | Description         |
| ------------------------------- | ------------------- |
| `createBusiness()`              | Create business entity |
| `createUser()`                  | Create organization user |
| `createUserToken()`             | Create user token |
| `getSuborganizations()`         | List sub-organizations |
| `getSuborganizationDetails()`   | Get sub-org details |

### SubmerchantService

| Method                   | Description         |
| ------------------------ | ------------------- |
| `create()`               | Create submerchant  |
| `get()`                  | Get submerchant details |
| `update()`               | Update submerchant  |
| `delete()`               | Delete submerchant  |
| `list()`                 | List submerchants   |

### SubscriptionService

| Method                   | Description         |
| ------------------------ | ------------------- |
| `create()`               | Create subscription |
| `get()`                  | Get subscription    |
| `list()`                 | List subscriptions  |
| `cancel()`               | Cancel subscription |

### ElementsService

| Method                   | Description         |
| ------------------------ | ------------------- |
| `createSession()`        | Create an Elements session: `POST /api/v1/elements/sessions` |
| `getSession()`           | Get a session's outcome: `GET /api/v1/elements/sessions/{id}` |
| `cancelSession()`        | Cancel a session: `POST /api/v1/elements/sessions/{id}/cancel` |


## Advanced Options

### Error Handling

```java
try {
    client.orders().create(request);
} catch (TapsilatException e) {
    System.err.println("API Error: " + e.getMessage());
}
```

An error response from the API is thrown as `ApiException` (HTTP 401 and 403 as its subclass `AuthenticationException`), with:

- `getStatusCode()`: HTTP status code
- `getErrorCode()`: API error code, when the body has one
- `getErrorMessage()`: API error message, when the body has one
- `isRetryable()`: true when the error object says the request can be retried
- `getResponseBody()`: original response body

Elements routes nest the error as `{"error": {"code": "...", "message": "...", "retryable": true}}`; the code, message and retryable flag are read from that object.

```java
try {
    client.elements().getSession(sessionId);
} catch (ApiException e) {
    if ("session_not_found".equals(e.getErrorCode())) {
        // unknown session
    } else if (e.isRetryable()) {
        // try again later
    }
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
