package com.tapsilat.unit.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.enums.Currency;
import com.tapsilat.enums.Locale;
import com.tapsilat.model.common.*;
import com.tapsilat.model.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderModelTest {
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL);
    }

    @Test
    void testOrderCreateRequestSerializationFull() throws JsonProcessingException {
        Buyer buyer = new Buyer("John", "Doe", "test@example.com");
        buyer.setGsmNumber("+905551234567");
        buyer.setIdentityNumber("11111111111");
        buyer.setCity("Istanbul");
        buyer.setCountry("TR");
        buyer.setRegistrationAddress("Reg Address");
        buyer.setIp("127.0.0.1");

        BasketItemPayer payer = new BasketItemPayer();
        payer.setAddress("Payer Addr");
        payer.setReferenceId("payer-ref");
        payer.setTaxOffice("TaxOfc");
        payer.setType("PERSONAL");
        BasketItem item = new BasketItem();
        item.setId("itm-1");
        item.setName("Laptop");
        item.setItemType("PHYSICAL");
        item.setPrice(100.0);
        item.setQuantity(1);
        item.setPayer(payer);
        
        BillingAddress billing = new BillingAddress();
        billing.setAddress("Bill Addr");
        billing.setBillingType("PERSONAL");
        billing.setCountry("TR");
        billing.setCity("Istanbul");
        billing.setContactName("Contact");
        
        OrderConsent consent = new OrderConsent("Consent Title", "http://consent");
        
        OrderCard card = new OrderCard();
        card.setCardId("card-1");
        card.setCardSequence(1);
        
        PaymentTerm term = new PaymentTerm();
        term.setAmount(new BigDecimal("50.0"));
        term.setTermReferenceId("term-ref");
        term.setTermSequence(1);
        term.setStatus("PENDING");
        
        OrderPFSubMerchant pfSub = new OrderPFSubMerchant();
        pfSub.setId("pf-1");
        pfSub.setName("PF Name");
        pfSub.setMcc("1234");
        
        ShippingAddress shipping = new ShippingAddress("Ship Addr", "City", "Contact Name", "TR", "34000");

        SubOrganization subOrg = new SubOrganization();
        subOrg.setOrganizationName("Org Name");
        subOrg.setLegalCompanyTitle("Legal Title");
        subOrg.setSubMerchantKey("sm-key");
        subOrg.setAcquirer("ACQ");

        Submerchant subMerch = new Submerchant();
        subMerch.setAmount(new BigDecimal("40.0"));
        subMerch.setOrderBasketItemId("itm-1");
        
        CheckoutDesign design = new CheckoutDesign();
        design.setInputBackgroundColor("#FFF");

        OrderCreateRequest order = new OrderCreateRequest();
        order.setAmount(new BigDecimal("150.0"));
        order.setCurrency(Currency.TRY.getCode());
        order.setLocale(Locale.TR.getCode());
        order.setBuyer(buyer);
        order.setBasketItems(Collections.singletonList(item));
        order.setBillingAddress(billing);
        order.setCheckoutDesign(design);
        order.setConsents(Collections.singletonList(consent));
        order.setOrderCards(Collections.singletonList(card));
        order.setPaymentTerms(Collections.singletonList(term));
        order.setPfSubMerchant(pfSub);
        order.setShippingAddress(shipping);
        order.setSubOrganization(subOrg);
        order.setSubmerchants(Collections.singletonList(subMerch));
        order.setThreeDForce(true);

        String json = mapper.writeValueAsString(order);
        JsonNode node = mapper.readTree(json);

        assertEquals(150.0, node.get("amount").asDouble());
        assertEquals("TRY", node.get("currency").asText());
        assertEquals("tr", node.get("locale").asText());
        assertEquals("John", node.get("buyer").get("name").asText());
        assertEquals("TR", node.get("buyer").get("country").asText());
        assertEquals("Laptop", node.get("basket_items").get(0).get("name").asText());
        assertEquals("Payer Addr", node.get("basket_items").get(0).get("payer").get("address").asText());
        assertEquals("Bill Addr", node.get("billing_address").get("address").asText());
        assertEquals("#FFF", node.get("checkout_design").get("input_background_color").asText());
        assertEquals("Consent Title", node.get("consents").get(0).get("title").asText());
        assertEquals("card-1", node.get("order_cards").get(0).get("card_id").asText());
        assertEquals(50.0, node.get("payment_terms").get(0).get("amount").asDouble());
        assertEquals("PF Name", node.get("pf_sub_merchant").get("name").asText());
        assertEquals("Ship Addr", node.get("shipping_address").get("address").asText());
        assertEquals("ACQ", node.get("sub_organization").get("acquirer").asText());
        assertEquals(40.0, node.get("submerchants").get(0).get("amount").asDouble());
        assertTrue(node.get("three_d_force").asBoolean());
    }

    @Test
    void testRefundOrderRequestSerialization() throws JsonProcessingException {
        RefundOrderRequest refund = new RefundOrderRequest();
        refund.setReferenceId("ref123");
        refund.setAmount(new BigDecimal("50.0"));
        refund.setOrderItemId("item001");
        
        String json = mapper.writeValueAsString(refund);
        JsonNode node = mapper.readTree(json);
        
        assertEquals("50.0", node.get("amount").asText());
        assertEquals("ref123", node.get("reference_id").asText());
        assertEquals("item001", node.get("order_item_id").asText());
        assertFalse(node.has("order_item_payment_id"));
    }
}
