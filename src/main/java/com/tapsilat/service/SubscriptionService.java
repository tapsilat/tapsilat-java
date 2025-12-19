package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.subscription.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionService extends BaseService {

    public SubscriptionService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    public SubscriptionCreateResponse create(SubscriptionCreateRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_SUBSCRIPTION_CREATE, request, null),
                    SubscriptionCreateResponse.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create subscription", e);
        }
    }

    public SubscriptionDetail get(SubscriptionGetRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_SUBSCRIPTION, request, null),
                    SubscriptionDetail.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get subscription", e);
        }
    }

    public List<SubscriptionListItem> list(Integer page, Integer perPage) throws TapsilatException {
        try {
            Map<String, String> params = new HashMap<>();
            if (page != null)
                params.put("page", String.valueOf(page));
            if (perPage != null)
                params.put("per_page", String.valueOf(perPage));

            SubscriptionListItem[] items = executeRequest(
                    buildRequest("GET", TapsilatConstants.ENDPOINT_SUBSCRIPTION_LIST, null, params),
                    SubscriptionListItem[].class);
            return items != null ? java.util.Arrays.asList(items) : java.util.Collections.emptyList();
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to list subscriptions", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> cancel(SubscriptionCancelRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_SUBSCRIPTION_CANCEL, request, null),
                    Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to cancel subscription", e);
        }
    }

    public SubscriptionRedirectResponse redirect(SubscriptionRedirectRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_SUBSCRIPTION_REDIRECT, request, null),
                    SubscriptionRedirectResponse.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to redirect subscription", e);
        }
    }
}
