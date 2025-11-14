package com.tapsilat.order;

import com.tapsilat.order.config.TapsilatConfig;
import com.tapsilat.order.enums.Currency;
import com.tapsilat.order.enums.Locale;
import com.tapsilat.order.model.*;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Comprehensive example showing how to create an order with all available fields.
 * This matches the complete API structure from the curl example.
 */
public class TapsilatClientComprehensiveExample {
    
    public static void main(String[] args) {
        try {
            // Configure the client
            TapsilatConfig config = new TapsilatConfig();
            config.setBaseUrl("https://panel.tapsilat.dev");
            config.setBearerToken("your-bearer-token");
            
            try (TapsilatClient client = new TapsilatClient(config)) {
                
                // Create buyer information
                Buyer buyer = new Buyer("John", "Doe", "johndoe@example.com");
                buyer.setPhone("+905321234567");
                buyer.setIdentityNumber("12345678901");
                
                // Create shipping address
                ShippingAddress shippingAddress = new ShippingAddress(
                    "Shipping Address Line 1",
                    "Istanbul",
                    "John Doe",
                    "Turkey",
                    "34000"
                );
                
                // Create billing address
                BillingAddress billingAddress = new BillingAddress();
                billingAddress.setAddress("Billing Address Line 1");
                billingAddress.setCity("Istanbul");
                billingAddress.setContactName("John Doe");
                billingAddress.setCountry("Turkey");
                billingAddress.setZipCode("34000");
                billingAddress.setBillingType("PERSONAL");
                billingAddress.setContactPhone("+905321234567");
                billingAddress.setDistrict("Kadikoy");
                billingAddress.setVatNumber("");
                
                // Create basket items
                BasketItem item1 = new BasketItem();
                item1.setId("item-1");
                item1.setName("Product 1");
                item1.setCategory1("Category 1");
                item1.setCategory2("Category 2");
                item1.setPrice(100.0);
                item1.setQuantity(1);
                item1.setItemType("PHYSICAL");
                item1.setCouponDiscount(0.0);
                item1.setData("{}");
                item1.setQuantityUnit("pcs");
                
                BasketItem item2 = new BasketItem();
                item2.setId("item-2");
                item2.setName("Product 2");
                item2.setCategory1("Category 1");
                item2.setCategory2("Category 2");
                item2.setPrice(50.0);
                item2.setQuantity(1);
                item2.setItemType("PHYSICAL");
                item2.setCouponDiscount(0.0);
                item2.setData("{}");
                item2.setQuantityUnit("pcs");
                
                // Create metadata
                Metadata metadata1 = new Metadata("order_type", "standard");
                Metadata metadata2 = new Metadata("customer_segment", "premium");
                
                // Build the complete order request
                OrderRequest orderRequest = new OrderRequest(
                    new BigDecimal("150.00"),
                    Currency.TRY.getCode(),
                    Locale.TR.getCode(),
                    buyer
                );
                
                orderRequest.setDescription("Test Order with Complete Information");
                orderRequest.setCallbackUrl("https://example.com/callback");
                orderRequest.setConversationId("test-order-" + System.currentTimeMillis());
                orderRequest.setShippingAddress(shippingAddress);
                orderRequest.setBillingAddress(billingAddress);
                orderRequest.setBasketItems(Arrays.asList(item1, item2));
                orderRequest.setMetadata(Arrays.asList(metadata1, metadata2));
                orderRequest.setTaxAmount(0.0);
                orderRequest.setThreeDForce(true);
                orderRequest.setPartialPayment(false);
                orderRequest.setPaymentMethods(Arrays.asList("CREDIT_CARD", "DEBIT_CARD"));
                orderRequest.setPaymentOptions(Arrays.asList("INSTALLMENT"));
                
                // Create the order
                System.out.println("Creating order with comprehensive information...");
                OrderResponse response = client.createOrder(orderRequest);
                
                // Display response
                System.out.println("Order created successfully!");
                System.out.println("Order ID: " + response.getOrderId());
                System.out.println("Reference ID: " + response.getReferenceId());
                
            } catch (Exception e) {
                System.err.println("Error creating order: " + e.getMessage());
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            System.err.println("Configuration error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
