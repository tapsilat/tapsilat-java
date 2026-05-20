package com.tapsilat.constants;

/**
 * Constants used throughout the Tapsilat SDK.
 */
public final class TapsilatConstants {

    private TapsilatConstants() {
        // Utility class - prevent instantiation
    }

    // HTTP Headers
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String MEDIA_TYPE_JSON = "application/json";

    // API Endpoints
    public static final String ENDPOINT_CREATE_ORDER = "/api/v1/order/create";
    public static final String ENDPOINT_ORDER_ACCOUNTING = "/api/v1/order/accounting";
    public static final String ENDPOINT_ORDER_POSTAUTH = "/api/v1/order/postauth";
    public static final String ENDPOINT_SYSTEM_ORDER_STATUSES = "/api/v1/system/order-statuses";
    public static final String ENDPOINT_ORDER_LIST = "/api/v1/order/list";
    public static final String ENDPOINT_ORDER_SUBMERCHANTS = "/api/v1/order/submerchants";
    public static final String ENDPOINT_ORDER_CANCEL = "/api/v1/order/cancel";
    public static final String ENDPOINT_ORDER_REFUND = "/api/v1/order/refund";
    public static final String ENDPOINT_ORDER_REFUND_ALL = "/api/v1/order/refund-all";
    public static final String ENDPOINT_ORDER_PAYMENT_DETAILS = "/api/v1/order/payment-details";
    public static final String ENDPOINT_ORDER_TERM = "/api/v1/order/term";
    public static final String ENDPOINT_ORDER_TERM_DELETE = "/api/v1/order/term";
    public static final String ENDPOINT_ORDER_TERM_UPDATE = "/api/v1/order/term";
    public static final String ENDPOINT_ORDER_TERM_REFUND = "/api/v1/order/term/refund";
    public static final String ENDPOINT_ORDER_TERMINATE = "/api/v1/order/terminate";
    public static final String ENDPOINT_ORDER_MANUAL_CALLBACK = "/api/v1/order/callback";
    public static final String ENDPOINT_ORDER_RELATED_UPDATE = "/api/v1/order/releated";
    public static final String ENDPOINT_ORGANIZATION_SETTINGS = "/api/v1/organization/settings";
    public static final String ENDPOINT_ORGANIZATION_CALLBACK = "/api/v1/organization/callback";
    public static final String ENDPOINT_ORGANIZATION_BUSINESS_CREATE = "/api/v1/organization/business/create";
    public static final String ENDPOINT_ORGANIZATION_CURRENCIES = "/api/v1/organization/currencies";
    public static final String ENDPOINT_ORGANIZATION_LIMIT_USER = "/api/v1/organization/limit/user";
    public static final String ENDPOINT_ORGANIZATION_LIMITS = "/api/v1/organization/limits";
    public static final String ENDPOINT_ORGANIZATION_VPOS = "/api/v1/organization/list-vpos";
    public static final String ENDPOINT_ORGANIZATION_META = "/api/v1/organization/meta";
    public static final String ENDPOINT_ORGANIZATION_SCOPES = "/api/v1/organization/scopes";
    public static final String ENDPOINT_ORGANIZATION_SUBORGANIZATIONS = "/api/v1/organization/suborganizations";
    public static final String ENDPOINT_ORGANIZATION_USER_CREATE = "/api/v1/organization/user/create";
    public static final String ENDPOINT_ORGANIZATION_USER_VERIFY = "/api/v1/organization/user/verify";
    public static final String ENDPOINT_ORGANIZATION_USER_VERIFY_MOBILE = "/api/v1/organization/user/verify-mobile";
    public static final String ENDPOINT_SUBSCRIPTION = "/api/v1/subscription";
    public static final String ENDPOINT_SUBSCRIPTION_CANCEL = "/api/v1/subscription/cancel";
    public static final String ENDPOINT_SUBSCRIPTION_CREATE = "/api/v1/subscription/create";
    public static final String ENDPOINT_SUBSCRIPTION_LIST = "/api/v1/subscription/list";
    public static final String ENDPOINT_SUBSCRIPTION_REDIRECT = "/api/v1/subscription/redirect";
    public static final String ENDPOINT_ORGANIZATION_CURRENCY_PRESETS = "/api/v1/organization/currency-presets";
    public static final String ENDPOINT_ORGANIZATION_SUBORGANIZATION_DETAILS = "/api/v1/organization/suborganizations/%s";
    public static final String ENDPOINT_ORGANIZATION_SUBORGANIZATION_SUBMERCHANTS = "/api/v1/organization/suborganizations/%s/submerchant";
    public static final String ENDPOINT_HEALTH = "/api/v1/health";

    // Default Configuration
    public static final String DEFAULT_BASE_URL = "https://panel.tapsilat.com";
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000; // 30 seconds
    public static final int DEFAULT_READ_TIMEOUT = 60000; // 60 seconds

    // Validation Limits
    public static final int MAX_DESCRIPTION_LENGTH = 500;
    public static final int MAX_CONVERSATION_ID_LENGTH = 255;

    // HTTP Status Codes
    public static final int HTTP_OK = 200;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_INTERNAL_ERROR = 500;
}
