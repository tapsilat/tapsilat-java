package com.tapsilat.config;

/**
 * Configuration class for Tapsilat API client.
 */
public class TapsilatConfig {
    
    private String bearerToken;
    private String baseUrl;
    private int connectionTimeout;
    private int readTimeout;
    
        // Default values
        private static final String DEFAULT_BASE_URL = System.getenv().getOrDefault(
            "TAPSILAT_BASE_URL",
            "https://panel.tapsilat.dev"
        );
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000; // 30 seconds
    private static final int DEFAULT_READ_TIMEOUT = 60000; // 60 seconds
    
    // Default constructor
    public TapsilatConfig() {
        this.baseUrl = DEFAULT_BASE_URL;
        this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        this.readTimeout = DEFAULT_READ_TIMEOUT;
    }
    
    // Constructor with all fields
    public TapsilatConfig(String baseUrl, int connectionTimeout, int readTimeout) {
        this.baseUrl = baseUrl != null ? baseUrl : DEFAULT_BASE_URL;
        this.connectionTimeout = connectionTimeout > 0 ? connectionTimeout : DEFAULT_CONNECTION_TIMEOUT;
        this.readTimeout = readTimeout > 0 ? readTimeout : DEFAULT_READ_TIMEOUT;
    }
    
    // Getters and Setters
    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }
    
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public int getConnectionTimeout() {
        return connectionTimeout;
    }
    
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
    
    public int getReadTimeout() {
        return readTimeout;
    }
    
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
    
    @Override
    public String toString() {
        return "TapsilatConfig{" +
                "bearerToken='" + (bearerToken != null ? "***" : "null") + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                '}';
    }
} 
