package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.order.*;
import com.tapsilat.validation.OrderRequestValidator;
import com.tapsilat.validation.ValidationException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrderService extends BaseService {

    public OrderService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    public OrderResponse create(OrderCreateRequest orderRequest) throws TapsilatException {
        try {
            OrderRequestValidator.validateOrThrow(orderRequest);
            OrderResponse response = executeRequest(
                    buildRequest("POST", TapsilatConstants.ENDPOINT_CREATE_ORDER, orderRequest, null),
                    OrderResponse.class);

            // Parity with Python: fallback to fetch checkout URL if not present
            if (response != null && response.getOrderId() != null
                    && (response.getCheckoutUrl() == null || response.getCheckoutUrl().isEmpty())) {
                try {
                    logger.info("Checkout URL missing for order {}, fetching details...", response.getReferenceId());
                    OrderResponse details = get(response.getReferenceId());
                    if (details != null) {
                        logger.info("Fetched details for order {}: checkoutUrl={}", response.getReferenceId(),
                                details.getCheckoutUrl());
                        response.setCheckoutUrl(details.getCheckoutUrl());
                    } else {
                        logger.warn("Fetched details for order {} were null", response.getReferenceId());
                    }
                } catch (Exception e) {
                    logger.error("Failed to fetch fallback checkout URL for order {}: {}", response.getReferenceId(),
                            e.getMessage(), e);
                }
            }
            return response;
        } catch (ValidationException e) {
            throw new TapsilatException(e.getMessage(), e);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create order", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> accounting(OrderAccountingRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_ACCOUNTING, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed order accounting", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> postAuth(OrderPostAuthRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_POSTAUTH, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed post-auth", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSystemOrderStatuses() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_ORDER_STATUSES, null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system order statuses", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSubmerchants(Integer page, Integer perPage) throws TapsilatException {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null)
                params.put("page", page.toString());
            if (perPage != null)
                params.put("per_page", perPage.toString());
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORDER_SUBMERCHANTS, null, params),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get submerchants", e);
        }
    }

    public OrderResponse get(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/api/v1/order/" + referenceId, null, null), OrderResponse.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order", e);
        }
    }

    public OrderResponse getByConversationId(String conversationId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/api/v1/order/conversation/" + conversationId, null, null),
                    OrderResponse.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order by conversation id", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> list(Integer page, Integer perPage, String startDate, String endDate,
            String organizationId, String relatedReferenceId, String buyerId, Integer status) throws TapsilatException {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null)
                params.put("page", page.toString());
            if (perPage != null)
                params.put("per_page", perPage.toString());
            if (startDate != null)
                params.put("start_date", startDate);
            if (endDate != null)
                params.put("end_date", endDate);
            if (organizationId != null)
                params.put("organization_id", organizationId);
            if (relatedReferenceId != null)
                params.put("related_reference_id", relatedReferenceId);
            if (buyerId != null)
                params.put("buyer_id", buyerId);
            if (status != null)
                params.put("status", status.toString());

            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORDER_LIST, null, params), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to list orders", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> cancel(String referenceId) throws TapsilatException {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("reference_id", referenceId);
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_CANCEL, payload, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to cancel order", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> refund(RefundOrderRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_REFUND, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to refund order", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> refundAll(String referenceId) throws TapsilatException {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("reference_id", referenceId);
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_REFUND_ALL, payload, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to refund all", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPaymentDetails(String referenceId, String conversationId) throws TapsilatException {
        try {
            if (conversationId != null && !conversationId.isEmpty()) {
                Map<String, String> payload = new HashMap<>();
                payload.put("reference_id", referenceId);
                payload.put("conversation_id", conversationId);
                return executeRequest(
                        buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_PAYMENT_DETAILS, payload, null),
                        Map.class);
            }
            return executeRequest(buildRequest("GET", "/api/v1/order/" + referenceId + "/payment-details", null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get payment details", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getStatus(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/api/v1/order/" + referenceId + "/status", null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order status", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTransactions(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/api/v1/order/" + referenceId + "/transactions", null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get transactions", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTerm(String termReferenceId) throws TapsilatException {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("term_reference_id", termReferenceId);
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORDER_TERM, null, params), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order term", e);
        }
    }

    // Term management
    @SuppressWarnings("unchecked")
    public Map<String, Object> createTerm(OrderPaymentTermCreateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_TERM, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create term", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> deleteTerm(String orderId, String termReferenceId) throws TapsilatException {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("order_id", orderId);
            payload.put("term_reference_id", termReferenceId);
            return executeRequest(buildRequest("DELETE", TapsilatConstants.ENDPOINT_ORDER_TERM_DELETE, payload, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to delete term", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> updateTerm(OrderPaymentTermUpdateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("PATCH", TapsilatConstants.ENDPOINT_ORDER_TERM_UPDATE, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to update term", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> refundTerm(OrderTermRefundRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_TERM_REFUND, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to refund term", e);
        }
    }



    @SuppressWarnings("unchecked")
    public Map<String, Object> terminate(String referenceId) throws TapsilatException {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("reference_id", referenceId);
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_TERMINATE, payload, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to terminate order", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> manualCallback(OrderManualCallbackRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_MANUAL_CALLBACK, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed manual callback", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> relatedUpdate(String referenceId, String relatedReferenceId) throws TapsilatException {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("reference_id", referenceId);
            payload.put("related_reference_id", relatedReferenceId);
            return executeRequest(buildRequest("PATCH", TapsilatConstants.ENDPOINT_ORDER_RELATED_UPDATE, payload, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed related update", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> addBasketItem(AddBasketItemRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", "/api/v1/order/basket-item", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to add basket item", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> removeBasketItem(RemoveBasketItemRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("DELETE", "/api/v1/order/basket-item", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to remove basket item", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> updateBasketItem(UpdateBasketItemRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("PATCH", "/api/v1/order/basket-item", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to update basket item", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getOrganizationSettings() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_SETTINGS, null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization settings", e);
        }
    }

    public String getCheckoutUrl(String referenceId) throws TapsilatException {
        OrderResponse response = get(referenceId);
        if (response != null) {
            return response.getCheckoutUrl();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPaymentDetailsById(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/api/v1/order/" + referenceId + "/payment-details", null, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get payment details by id", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> updatePaymentOptions(OrderPaymentOptionsUpdateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("PATCH", TapsilatConstants.ENDPOINT_ORDER_PAYMENT_OPTIONS_UPDATE, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to update payment options", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> splitOrderItemPayment(SplitOrderItemPaymentRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORDER_SPLIT_ITEM_PAYMENT, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to split order item payment", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> vposQuery(String id) throws TapsilatException {
        try {
            String endpoint = String.format(TapsilatConstants.ENDPOINT_ORDER_VPOS_QUERY, id);
            return executeRequest(buildRequest("GET", endpoint, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to query vpos", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> callback(String id) throws TapsilatException {
        try {
            String endpoint = String.format(TapsilatConstants.ENDPOINT_ORDER_CALLBACK, id);
            return executeRequest(buildRequest("GET", endpoint, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get callback", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPayments(String orderId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/order/" + orderId + "/payments", null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order payments", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> pdf(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/order/" + referenceId + "/pdf", null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order pdf", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> excel(String referenceId) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", "/order/" + referenceId + "/excel", null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get order excel", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> refundRequest(com.tapsilat.model.order.RefundRequestModel request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", "/order/refund-request", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create refund request", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> addOip(com.tapsilat.model.order.AddOrderOipRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", "/order/oip", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to add order OIP", e);
        }
    }
}
