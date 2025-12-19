package com.tapsilat.model.order;

import com.tapsilat.model.common.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents an order creation request for Tapsilat payment API.
 * Required fields: amount, currency, locale, buyer.
 * Optional fields: description, callbackUrl, conversationId, metadata,
 * shippingAddress,
 * billingAddress, basketItems, taxAmount, threeDForce, partialPayment,
 * paymentMethods, paymentOptions.
 */
public class OrderRequest {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("buyer")
    private Buyer buyer;

    @JsonProperty("description")
    private String description;

    @JsonProperty("callbackUrl")
    private String callbackUrl;

    @JsonProperty("conversationId")
    private String conversationId;

    @JsonProperty("metadata")
    private List<Metadata> metadata;

    @JsonProperty("shipping_address")
    private ShippingAddress shippingAddress;

    @JsonProperty("billing_address")
    private BillingAddress billingAddress;

    @JsonProperty("basket_items")
    private List<BasketItem> basketItems;

    @JsonProperty("tax_amount")
    private Double taxAmount;

    @JsonProperty("three_d_force")
    private Boolean threeDForce;

    @JsonProperty("partial_payment")
    private Boolean partialPayment;

    @JsonProperty("payment_methods")
    private List<String> paymentMethods;

    @JsonProperty("payment_options")
    private List<String> paymentOptions;

    @JsonProperty("checkout_design")
    private CheckoutDesign checkoutDesign;

    @JsonProperty("enabled_installments")
    private List<Integer> enabledInstallments;

    @JsonProperty("external_reference_id")
    private String externalReferenceId;

    @JsonProperty("order_cards")
    private OrderCard orderCards;

    @JsonProperty("paid_amount")
    private BigDecimal paidAmount;

    @JsonProperty("payment_failure_url")
    private String paymentFailureUrl;

    @JsonProperty("payment_mode")
    private String paymentMode;

    @JsonProperty("payment_success_url")
    private String paymentSuccessUrl;

    @JsonProperty("payment_terms")
    private List<PaymentTerm> paymentTerms;

    @JsonProperty("pf_sub_merchant")
    private OrderPFSubMerchant pfSubMerchant;

    @JsonProperty("redirect_failure_url")
    private String redirectFailureUrl;

    @JsonProperty("redirect_success_url")
    private String redirectSuccessUrl;

    @JsonProperty("sub_organization")
    private SubOrganization subOrganization;

    @JsonProperty("submerchants")
    private List<Submerchant> submerchants;

    // Default constructor for Jackson deserialization
    public OrderRequest() {
    }

    /**
     * Constructor with required fields.
     * 
     * @param amount   Payment amount (required, must be positive)
     * @param currency Currency code (required)
     * @param locale   Locale code (required)
     * @param buyer    Buyer information (required)
     * @throws NullPointerException if any required parameter is null
     */
    public OrderRequest(BigDecimal amount, String currency, String locale, Buyer buyer) {
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
        this.locale = Objects.requireNonNull(locale, "Locale cannot be null");
        this.buyer = Objects.requireNonNull(buyer, "Buyer cannot be null");
    }

    // Getters and Setters
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = Objects.requireNonNull(amount, "Amount cannot be null");
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = Objects.requireNonNull(currency, "Currency cannot be null");
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = Objects.requireNonNull(locale, "Locale cannot be null");
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = Objects.requireNonNull(buyer, "Buyer cannot be null");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * Gets the metadata list.
     * 
     * @return Unmodifiable view of metadata list, or null if not set
     */
    public List<Metadata> getMetadata() {
        return metadata != null ? Collections.unmodifiableList(metadata) : null;
    }

    /**
     * Sets the metadata list with defensive copy.
     * 
     * @param metadata The metadata list (can be null)
     */
    public void setMetadata(List<Metadata> metadata) {
        this.metadata = metadata != null ? new ArrayList<>(metadata) : null;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Gets the basket items list.
     * 
     * @return Unmodifiable view of basket items list, or null if not set
     */
    public List<BasketItem> getBasketItems() {
        return basketItems != null ? Collections.unmodifiableList(basketItems) : null;
    }

    /**
     * Sets the basket items list with defensive copy.
     * 
     * @param basketItems The basket items list (can be null)
     */
    public void setBasketItems(List<BasketItem> basketItems) {
        this.basketItems = basketItems != null ? new ArrayList<>(basketItems) : null;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Boolean getThreeDForce() {
        return threeDForce;
    }

    public void setThreeDForce(Boolean threeDForce) {
        this.threeDForce = threeDForce;
    }

    public Boolean getPartialPayment() {
        return partialPayment;
    }

    public void setPartialPayment(Boolean partialPayment) {
        this.partialPayment = partialPayment;
    }

    /**
     * Gets the payment methods list.
     * 
     * @return Unmodifiable view of payment methods list, or null if not set
     */
    public List<String> getPaymentMethods() {
        return paymentMethods != null ? Collections.unmodifiableList(paymentMethods) : null;
    }

    /**
     * Sets the payment methods list with defensive copy.
     * 
     * @param paymentMethods The payment methods list (can be null)
     */
    public void setPaymentMethods(List<String> paymentMethods) {
        this.paymentMethods = paymentMethods != null ? new ArrayList<>(paymentMethods) : null;
    }

    /**
     * Gets the payment options list.
     * 
     * @return Unmodifiable view of payment options list, or null if not set
     */
    public List<String> getPaymentOptions() {
        return paymentOptions != null ? Collections.unmodifiableList(paymentOptions) : null;
    }

    /**
     * Sets the payment options list with defensive copy.
     * 
     * @param paymentOptions The payment options list (can be null)
     */
    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions != null ? new ArrayList<>(paymentOptions) : null;
    }

    public CheckoutDesign getCheckoutDesign() {
        return checkoutDesign;
    }

    public void setCheckoutDesign(CheckoutDesign checkoutDesign) {
        this.checkoutDesign = checkoutDesign;
    }

    public List<Integer> getEnabledInstallments() {
        return enabledInstallments;
    }

    public void setEnabledInstallments(List<Integer> enabledInstallments) {
        this.enabledInstallments = enabledInstallments;
    }

    public String getExternalReferenceId() {
        return externalReferenceId;
    }

    public void setExternalReferenceId(String externalReferenceId) {
        this.externalReferenceId = externalReferenceId;
    }

    public OrderCard getOrderCards() {
        return orderCards;
    }

    public void setOrderCards(OrderCard orderCards) {
        this.orderCards = orderCards;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentFailureUrl() {
        return paymentFailureUrl;
    }

    public void setPaymentFailureUrl(String paymentFailureUrl) {
        this.paymentFailureUrl = paymentFailureUrl;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getPaymentSuccessUrl() {
        return paymentSuccessUrl;
    }

    public void setPaymentSuccessUrl(String paymentSuccessUrl) {
        this.paymentSuccessUrl = paymentSuccessUrl;
    }

    public List<PaymentTerm> getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(List<PaymentTerm> paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public OrderPFSubMerchant getPfSubMerchant() {
        return pfSubMerchant;
    }

    public void setPfSubMerchant(OrderPFSubMerchant pfSubMerchant) {
        this.pfSubMerchant = pfSubMerchant;
    }

    public String getRedirectFailureUrl() {
        return redirectFailureUrl;
    }

    public void setRedirectFailureUrl(String redirectFailureUrl) {
        this.redirectFailureUrl = redirectFailureUrl;
    }

    public String getRedirectSuccessUrl() {
        return redirectSuccessUrl;
    }

    public void setRedirectSuccessUrl(String redirectSuccessUrl) {
        this.redirectSuccessUrl = redirectSuccessUrl;
    }

    public SubOrganization getSubOrganization() {
        return subOrganization;
    }

    public void setSubOrganization(SubOrganization subOrganization) {
        this.subOrganization = subOrganization;
    }

    public List<Submerchant> getSubmerchants() {
        return submerchants;
    }

    public void setSubmerchants(List<Submerchant> submerchants) {
        this.submerchants = submerchants;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                ", locale='" + locale + '\'' +
                ", buyer=" + buyer +
                ", description='" + description + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", metadata=" + metadata +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", basketItems=" + basketItems +
                ", taxAmount=" + taxAmount +
                ", threeDForce=" + threeDForce +
                ", partialPayment=" + partialPayment +
                ", paymentMethods=" + paymentMethods +
                ", paymentOptions=" + paymentOptions +
                '}';
    }
}