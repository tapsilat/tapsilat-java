package com.tapsilat.unit.utils;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponseAdapter;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.http.message.BasicClassicHttpResponse;

import java.io.IOException;

/**
 * A manual MockHttpClient that avoids Mockito instrumentation issues.
 * Uses CloseableHttpResponseAdapter to create real instances of the final CloseableHttpResponse class.
 */
public class MockHttpClient extends CloseableHttpClient {
    private ClassicHttpRequest capturedRequest;
    private int stubStatusCode = 200;
    private String stubResponseBody = "{}";

    public void setResponse(int statusCode, String body) {
        this.stubStatusCode = statusCode;
        this.stubResponseBody = body;
    }

    public ClassicHttpRequest getCapturedRequest() {
        return capturedRequest;
    }

    @Override
    protected CloseableHttpResponse doExecute(HttpHost target, ClassicHttpRequest request, HttpContext context) throws IOException {
        this.capturedRequest = request;
        
        // Create a real ClassicHttpResponse
        BasicClassicHttpResponse response = new BasicClassicHttpResponse(stubStatusCode);
        response.setEntity(new StringEntity(stubResponseBody));
        
        // Adapt it to CloseableHttpResponse using our package-private adapter trick
        return CloseableHttpResponseAdapter.adapt(response);
    }

    @Override
    public void close() throws IOException {}

    @Override
    public void close(CloseMode closeMode) {}
}
