package org.apache.hc.client5.http.impl.classic;

import org.apache.hc.core5.http.ClassicHttpResponse;

/**
 * Adapter to access the package-private CloseableHttpResponse.adapt method.
 */
public class CloseableHttpResponseAdapter {
    public static CloseableHttpResponse adapt(ClassicHttpResponse response) {
        return CloseableHttpResponse.adapt(response);
    }
}
