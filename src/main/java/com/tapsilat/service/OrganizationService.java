package com.tapsilat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.organization.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Map;

public class OrganizationService extends BaseService {

    public OrganizationService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCallback() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_CALLBACK, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization callback", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> updateCallback(CallbackURLDTO request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("PATCH", TapsilatConstants.ENDPOINT_ORGANIZATION_CALLBACK, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to update organization callback", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createBusiness(OrgCreateBusinessRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_BUSINESS_CREATE, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create organization business", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCurrencies() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_CURRENCIES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization currencies", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getLimitUser(String userId) throws TapsilatException {
        try {
            Map<String, String> params = new java.util.HashMap<>();
            if (userId != null) {
                params.put("user_id", userId);
            }
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_LIMIT_USER, null, params), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization limit user", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> setLimitUser(SetLimitUserRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_LIMIT_USER, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to set organization limit user", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getLimits() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_LIMITS, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization limits", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> listVpos(GetVposRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_VPOS, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to list organization vpos", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getMeta(String name) throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_META + "/" + name, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization meta", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getScopes() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_SCOPES, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization scopes", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSuborganizations(Integer page, Integer perPage) throws TapsilatException {
        try {
            Map<String, String> params = new java.util.HashMap<>();
            if (page != null) params.put("page", page.toString());
            if (perPage != null) params.put("per_page", perPage.toString());
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_SUBORGANIZATIONS, null, params), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization suborganizations", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createUser(OrgCreateUserRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_USER_CREATE, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create organization user", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> verifyUser(OrgUserVerifyRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_USER_VERIFY, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to verify organization user", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> verifyUserMobile(OrgUserMobileVerifyRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", TapsilatConstants.ENDPOINT_ORGANIZATION_USER_VERIFY_MOBILE, request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to verify organization user mobile", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getCurrencyPresets() throws TapsilatException {
        try {
            return executeRequest(buildRequest("GET", TapsilatConstants.ENDPOINT_ORGANIZATION_CURRENCY_PRESETS, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization currency presets", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSuborganizationDetails(String id) throws TapsilatException {
        try {
            String endpoint = String.format(TapsilatConstants.ENDPOINT_ORGANIZATION_SUBORGANIZATION_DETAILS, id);
            return executeRequest(buildRequest("GET", endpoint, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization suborganization details", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getSuborganizationSubmerchants(String id) throws TapsilatException {
        try {
            String endpoint = String.format(TapsilatConstants.ENDPOINT_ORGANIZATION_SUBORGANIZATION_SUBMERCHANTS, id);
            return executeRequest(buildRequest("GET", endpoint, null, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get organization suborganization submerchants", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createUserToken(com.tapsilat.model.organization.OrgUserTokenRequest request) throws TapsilatException {
        try {
            return executeRequest(buildRequest("POST", "/organization/user/token", request, null), Map.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create user token", e);
        }
    }
}
