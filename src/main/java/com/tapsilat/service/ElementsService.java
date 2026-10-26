package com.tapsilat.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.constants.TapsilatConstants;
import com.tapsilat.exception.TapsilatException;
import com.tapsilat.model.elements.ElementsSession;
import com.tapsilat.model.elements.ElementsSessionCreateRequest;
import com.tapsilat.model.elements.ElementsSessionWithSecret;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Tapsilat Elements sessions, for the native Android and iOS card sheet.
 * <p>
 * Your backend creates a session with its secret API key and passes only the session's client
 * secret to the app, which opens the sheet with it. Card data goes from the sheet to Tapsilat
 * encrypted and never reaches your backend.
 */
public class ElementsService extends BaseService {

    public ElementsService(CloseableHttpClient httpClient, TapsilatConfig config, ObjectMapper objectMapper) {
        super(httpClient, config, objectMapper);
    }

    /**
     * Creates an Elements session with {@code POST /api/v1/elements/sessions}.
     * <p>
     * Hand {@link ElementsSessionWithSecret#getClientSecret()} to the app that opens the card sheet.
     * It is returned only here; do not log or store it.
     *
     * @param request the session to create; {@code purpose} is required, and {@code pay} needs
     *                {@code order_reference_id}
     * @return the new session with its client secret
     */
    public ElementsSessionWithSecret createSession(ElementsSessionCreateRequest request) throws TapsilatException {
        Objects.requireNonNull(request, "request must not be null");
        try {
            return send(buildRequest("POST", TapsilatConstants.ENDPOINT_ELEMENTS_SESSIONS, request, null),
                    ElementsSessionWithSecret.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to create Elements session", e);
        }
    }

    /**
     * Returns the session's authoritative state and outcome with {@code GET /api/v1/elements/sessions/{id}}.
     * Read it on your backend before fulfilling, rather than trusting what the app reports.
     *
     * @param id the session id, for example {@code es_test_8Kq2nV0rX4}
     */
    public ElementsSession getSession(String id) throws TapsilatException {
        String endpoint = String.format(TapsilatConstants.ENDPOINT_ELEMENTS_SESSION, pathSegment(id));
        try {
            return send(buildRequest("GET", endpoint, null, null), ElementsSession.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to get Elements session", e);
        }
    }

    /**
     * Cancels a session that is still waiting for a card with
     * {@code POST /api/v1/elements/sessions/{id}/cancel}. A session that is confirming or has
     * completed comes back unchanged.
     *
     * @param id the session id
     */
    public ElementsSession cancelSession(String id) throws TapsilatException {
        String endpoint = String.format(TapsilatConstants.ENDPOINT_ELEMENTS_SESSION_CANCEL, pathSegment(id));
        try {
            return send(buildRequest("POST", endpoint, null, null), ElementsSession.class);
        } catch (IOException | ParseException e) {
            throw new TapsilatException("Failed to cancel Elements session", e);
        }
    }

    /**
     * Decodes the response without copying the body into parse error messages, so that a malformed
     * create response can never put the client secret into an exception.
     */
    private <T> T send(HttpUriRequestBase request, Class<T> responseType)
            throws IOException, ParseException, TapsilatException {
        String body = executeRequest(request, String.class);
        if (body == null) {
            return null;
        }
        return objectMapper.readerFor(responseType)
                .without(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION)
                .readValue(body);
    }

    private static String pathSegment(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("Elements session id must not be empty");
        }
        return URLEncoder.encode(id, StandardCharsets.UTF_8).replace("+", "%20");
    }
}
