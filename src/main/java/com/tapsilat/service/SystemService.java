package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.TapsilatException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Map;

public class SystemService extends BaseService {

    public SystemService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getOrderStatuses() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_ORDER_STATUSES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system order statuses", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getBasketItemTypes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_BASKET_ITEM_TYPES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system basket item types", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getErrorCodes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_ERROR_CODES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system error codes", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getPaymentTermStatuses() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_PAYMENT_TERM_STATUSES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system payment term statuses", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getProductTypes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_PRODUCT_TYPES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system product types", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getShortcutTypes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_SHORTCUT_TYPES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system shortcut types", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTransactionPaymentTypes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_TRANSACTION_PAYMENT_TYPES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system transaction payment types", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTransactionPurposes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_TRANSACTION_PURPOSES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system transaction purposes", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getTransactionStatuses() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_SYSTEM_TRANSACTION_STATUSES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get system transaction statuses", e);
        }
    }
}
