package com.tapsilat.order.builder;

import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;
import com.tapsilat.order.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder class for creating OrderRequest objects easily.
 */
public class OrderRequestBuilder {
    
    private BigDecimal amount;
    private String currency;
    private String locale;
    private Buyer buyer;
    private String description;
    private String callbackUrl;
    private String conversationId;
    private List<Metadata> metadata = new ArrayList<>();
    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;
    private List<BasketItem> basketItems = new ArrayList<>();
    private Double taxAmount;
    private Boolean threeDForce;
    private Boolean partialPayment;
    private List<String> paymentMethods = new ArrayList<>();
    private List<String> paymentOptions = new ArrayList<>();
    
    /**
     * Set the payment amount.
     * @param amount The amount
     * @return This builder
     */
    public OrderRequestBuilder amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    /**
     * Set the payment amount using double.
     * @param amount The amount
     * @return This builder
     */
    public OrderRequestBuilder amount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
        return this;
    }
    
    /**
     * Set the currency.
     * @param currency The currency code
     * @return This builder
     */
    public OrderRequestBuilder currency(String currency) {
        this.currency = currency;
        return this;
    }
    
    /**
     * Set the currency using enum.
     * @param currency The currency enum
     * @return This builder
     */
    public OrderRequestBuilder currency(Currency currency) {
        this.currency = currency.getCode();
        return this;
    }
    
    /**
     * Set the locale.
     * @param locale The locale code
     * @return This builder
     */
    public OrderRequestBuilder locale(String locale) {
        this.locale = locale;
        return this;
    }
    
    /**
     * Set the locale using enum.
     * @param locale The locale enum
     * @return This builder
     */
    public OrderRequestBuilder locale(Locale locale) {
        this.locale = locale.getCode();
        return this;
    }
    
    /**
     * Set the buyer information.
     * @param buyer The buyer object
     * @return This builder
     */
    public OrderRequestBuilder buyer(Buyer buyer) {
        this.buyer = buyer;
        return this;
    }
    
    /**
     * Set the buyer information using individual fields.
     * @param name The buyer name
     * @param surname The buyer surname
     * @param email The buyer email
     * @return This builder
     */
    public OrderRequestBuilder buyer(String name, String surname, String email) {
        this.buyer = new Buyer(name, surname, email);
        return this;
    }
    
    /**
     * Set the buyer information with all fields.
     * @param name The buyer name
     * @param surname The buyer surname
     * @param email The buyer email
     * @param phone The buyer phone (optional)
     * @param identityNumber The buyer identity number (optional)
     * @return This builder
     */
    public OrderRequestBuilder buyer(String name, String surname, String email, String phone, String identityNumber) {
        this.buyer = new Buyer(name, surname, email, phone, identityNumber);
        return this;
    }
    
    /**
     * Set the order description.
     * @param description The description
     * @return This builder
     */
    public OrderRequestBuilder description(String description) {
        this.description = description;
        return this;
    }
    
    /**
     * Set the callback URL.
     * @param callbackUrl The callback URL
     * @return This builder
     */
    public OrderRequestBuilder callbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }
    
    /**
     * Set the conversation ID.
     * @param conversationId The conversation ID
     * @return This builder
     */
    public OrderRequestBuilder conversationId(String conversationId) {
        this.conversationId = conversationId;
        return this;
    }
    
    /**
     * Add metadata key-value pair.
     * @param key The metadata key
     * @param value The metadata value
     * @return This builder
     */
    public OrderRequestBuilder metadata(String key, String value) {
        this.metadata.add(new Metadata(key, value));
        return this;
    }
    
    /**
     * Add metadata object.
     * @param metadata The metadata object
     * @return This builder
     */
    public OrderRequestBuilder metadata(Metadata metadata) {
        this.metadata.add(metadata);
        return this;
    }
    
    /**
     * Set the complete metadata list.
     * @param metadata The metadata list
     * @return This builder
     */
    public OrderRequestBuilder metadata(List<Metadata> metadata) {
        this.metadata = new ArrayList<>(metadata);
        return this;
    }
    
    /**
     * Set the shipping address.
     * @param shippingAddress The shipping address
     * @return This builder
     */
    public OrderRequestBuilder shippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }
    
    /**
     * Set the billing address.
     * @param billingAddress The billing address
     * @return This builder
     */
    public OrderRequestBuilder billingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }
    
    /**
     * Add a basket item.
     * @param basketItem The basket item to add
     * @return This builder
     */
    public OrderRequestBuilder basketItem(BasketItem basketItem) {
        this.basketItems.add(basketItem);
        return this;
    }
    
    /**
     * Set the complete basket items list.
     * @param basketItems The basket items list
     * @return This builder
     */
    public OrderRequestBuilder basketItems(List<BasketItem> basketItems) {
        this.basketItems = new ArrayList<>(basketItems);
        return this;
    }
    
    /**
     * Set the tax amount.
     * @param taxAmount The tax amount
     * @return This builder
     */
    public OrderRequestBuilder taxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }
    
    /**
     * Set whether to force 3D Secure authentication.
     * @param threeDForce True to force 3D Secure
     * @return This builder
     */
    public OrderRequestBuilder threeDForce(Boolean threeDForce) {
        this.threeDForce = threeDForce;
        return this;
    }
    
    /**
     * Set whether to allow partial payment.
     * @param partialPayment True to allow partial payment
     * @return This builder
     */
    public OrderRequestBuilder partialPayment(Boolean partialPayment) {
        this.partialPayment = partialPayment;
        return this;
    }
    
    /**
     * Add a payment method.
     * @param paymentMethod The payment method to add
     * @return This builder
     */
    public OrderRequestBuilder paymentMethod(String paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this;
    }
    
    /**
     * Set the complete payment methods list.
     * @param paymentMethods The payment methods list
     * @return This builder
     */
    public OrderRequestBuilder paymentMethods(List<String> paymentMethods) {
        this.paymentMethods = new ArrayList<>(paymentMethods);
        return this;
    }
    
    /**
     * Add a payment option.
     * @param paymentOption The payment option to add
     * @return This builder
     */
    public OrderRequestBuilder paymentOption(String paymentOption) {
        this.paymentOptions.add(paymentOption);
        return this;
    }
    
    /**
     * Set the complete payment options list.
     * @param paymentOptions The payment options list
     * @return This builder
     */
    public OrderRequestBuilder paymentOptions(List<String> paymentOptions) {
        this.paymentOptions = new ArrayList<>(paymentOptions);
        return this;
    }
    
    /**
     * Build the OrderRequest object.
     * @return The constructed OrderRequest
     */
    public OrderRequest build() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setAmount(amount);
        orderRequest.setCurrency(currency);
        orderRequest.setLocale(locale);
        orderRequest.setBuyer(buyer);
        orderRequest.setDescription(description);
        orderRequest.setCallbackUrl(callbackUrl);
        orderRequest.setConversationId(conversationId);
        orderRequest.setMetadata(metadata.isEmpty() ? null : metadata);
        orderRequest.setShippingAddress(shippingAddress);
        orderRequest.setBillingAddress(billingAddress);
        orderRequest.setBasketItems(basketItems.isEmpty() ? null : basketItems);
        orderRequest.setTaxAmount(taxAmount);
        orderRequest.setThreeDForce(threeDForce);
        orderRequest.setPartialPayment(partialPayment);
        orderRequest.setPaymentMethods(paymentMethods.isEmpty() ? null : paymentMethods);
        orderRequest.setPaymentOptions(paymentOptions.isEmpty() ? null : paymentOptions);
        return orderRequest;
    }
    
    /**
     * Create a new builder instance.
     * @return A new OrderRequestBuilder
     */
    public static OrderRequestBuilder newBuilder() {
        return new OrderRequestBuilder();
    }
} 