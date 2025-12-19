package com.tapsilat;

import com.tapsilat.model.common.*; import com.tapsilat.model.order.*; import com.tapsilat.model.subscription.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for model classes - equals, hashCode, toString.
 */
class ModelTest {
    
    @Test
    void testBuyerEqualsAndHashCode() {
        Buyer buyer1 = new Buyer("John", "Doe", "john@example.com");
        Buyer buyer2 = new Buyer("John", "Doe", "john@example.com");
        Buyer buyer3 = new Buyer("Jane", "Doe", "jane@example.com");
        
        // Test equals
        assertEquals(buyer1, buyer2);
        assertNotEquals(buyer1, buyer3);
        assertNotEquals(buyer1, null);
        assertNotEquals(buyer1, "string");
        
        // Test hashCode
        assertEquals(buyer1.hashCode(), buyer2.hashCode());
        assertNotEquals(buyer1.hashCode(), buyer3.hashCode());
        
        // Test with optional fields
        Buyer buyer4 = new Buyer("John", "Doe", "john@example.com", "+123", "123");
        Buyer buyer5 = new Buyer("John", "Doe", "john@example.com", "+123", "123");
        assertEquals(buyer4, buyer5);
        assertEquals(buyer4.hashCode(), buyer5.hashCode());
    }
    
    @Test
    void testMetadataEqualsAndHashCode() {
        Metadata meta1 = new Metadata("key1", "value1");
        Metadata meta2 = new Metadata("key1", "value1");
        Metadata meta3 = new Metadata("key2", "value2");
        
        assertEquals(meta1, meta2);
        assertNotEquals(meta1, meta3);
        assertEquals(meta1.hashCode(), meta2.hashCode());
    }
    
    @Test
    void testShippingAddressEqualsAndHashCode() {
        ShippingAddress addr1 = new ShippingAddress("123 Main St", "Istanbul", "John", "Turkey", "34000");
        ShippingAddress addr2 = new ShippingAddress("123 Main St", "Istanbul", "John", "Turkey", "34000");
        ShippingAddress addr3 = new ShippingAddress("456 Oak Ave", "Ankara", "Jane", "Turkey", "06000");
        
        assertEquals(addr1, addr2);
        assertNotEquals(addr1, addr3);
        assertEquals(addr1.hashCode(), addr2.hashCode());
    }
    
    @Test
    void testBillingAddressEqualsAndHashCode() {
        BillingAddress addr1 = new BillingAddress();
        addr1.setAddress("123 Main St");
        addr1.setCity("Istanbul");
        addr1.setBillingType("PERSONAL");
        
        BillingAddress addr2 = new BillingAddress();
        addr2.setAddress("123 Main St");
        addr2.setCity("Istanbul");
        addr2.setBillingType("PERSONAL");
        
        BillingAddress addr3 = new BillingAddress();
        addr3.setAddress("456 Oak Ave");
        addr3.setCity("Ankara");
        addr3.setBillingType("BUSINESS");
        
        assertEquals(addr1, addr2);
        assertNotEquals(addr1, addr3);
        assertEquals(addr1.hashCode(), addr2.hashCode());
    }
    
    @Test
    void testBasketItemEqualsAndHashCode() {
        BasketItem item1 = new BasketItem();
        item1.setId("item-1");
        item1.setName("Product 1");
        item1.setPrice(100.0);
        
        BasketItem item2 = new BasketItem();
        item2.setId("item-1");
        item2.setName("Product 1");
        item2.setPrice(100.0);
        
        BasketItem item3 = new BasketItem();
        item3.setId("item-2");
        item3.setName("Product 2");
        item3.setPrice(200.0);
        
        assertEquals(item1, item2);
        assertNotEquals(item1, item3);
        assertEquals(item1.hashCode(), item2.hashCode());
    }
    
    @Test
    void testOrderResponseEqualsAndHashCode() {
        OrderResponse resp1 = new OrderResponse("order-123", "ref-456");
        OrderResponse resp2 = new OrderResponse("order-123", "ref-456");
        OrderResponse resp3 = new OrderResponse("order-999", "ref-888");
        
        assertEquals(resp1, resp2);
        assertNotEquals(resp1, resp3);
        assertEquals(resp1.hashCode(), resp2.hashCode());
    }
    
    @Test
    void testToStringMethods() {
        Buyer buyer = new Buyer("John", "Doe", "john@example.com");
        assertTrue(buyer.toString().contains("John"));
        assertTrue(buyer.toString().contains("Doe"));
        
        Metadata metadata = new Metadata("key", "value");
        assertTrue(metadata.toString().contains("key"));
        assertTrue(metadata.toString().contains("value"));
        
        OrderResponse response = new OrderResponse("order-123", "ref-456");
        assertTrue(response.toString().contains("order-123"));
        assertTrue(response.toString().contains("ref-456"));
    }
}
