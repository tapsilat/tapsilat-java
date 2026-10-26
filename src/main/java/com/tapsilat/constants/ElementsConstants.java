package com.tapsilat.constants;

/**
 * Known values of Tapsilat Elements session fields.
 * <p>
 * The session models keep these fields as plain strings, so a value added to the API later
 * still decodes. Compare with {@code equals}, for example
 * {@code ElementsConstants.STATUS_SUCCEEDED.equals(session.getStatus())}.
 */
public final class ElementsConstants {

    private ElementsConstants() {
        // Utility class - prevent instantiation
    }

    // Session purposes
    public static final String PURPOSE_TOKENIZE = "tokenize";
    public static final String PURPOSE_PAY = "pay";

    // Tokenize modes
    /** 3D Secure. */
    public static final int MODE_SECURE = 1;
    public static final int MODE_HALF_SECURE = 2;
    public static final int MODE_NON_SECURE = 3;

    // Session statuses
    public static final String STATUS_REQUIRES_PAYMENT_METHOD = "requires_payment_method";
    public static final String STATUS_PROCESSING = "processing";
    public static final String STATUS_REQUIRES_ACTION = "requires_action";
    public static final String STATUS_SUCCEEDED = "succeeded";
    public static final String STATUS_FAILED = "failed";
    public static final String STATUS_EXPIRED = "expired";
    public static final String STATUS_CANCELED = "canceled";

    // Card brands
    public static final String BRAND_VISA = "visa";
    public static final String BRAND_MASTERCARD = "mastercard";
    public static final String BRAND_AMEX = "amex";
    public static final String BRAND_TROY = "troy";
    public static final String BRAND_DISCOVER = "discover";
    public static final String BRAND_UNIONPAY = "unionpay";
    public static final String BRAND_UNKNOWN = "unknown";
}
