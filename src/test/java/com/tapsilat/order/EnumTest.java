package com.tapsilat.order;

import com.tapsilat.order.enums.BillingType;
import com.tapsilat.order.enums.ItemType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for enum classes.
 */
class EnumTest {
    
    @Test
    void testBillingTypeEnum() {
        // Test enum values
        assertEquals("PERSONAL", BillingType.PERSONAL.getCode());
        assertEquals("BUSINESS", BillingType.BUSINESS.getCode());
        
        // Test fromCode
        assertEquals(BillingType.PERSONAL, BillingType.fromCode("PERSONAL"));
        assertEquals(BillingType.PERSONAL, BillingType.fromCode("personal"));
        assertEquals(BillingType.BUSINESS, BillingType.fromCode("BUSINESS"));
        assertEquals(BillingType.BUSINESS, BillingType.fromCode("business"));
        
        // Test invalid codes
        assertNull(BillingType.fromCode("INVALID"));
        assertNull(BillingType.fromCode(null));
        assertNull(BillingType.fromCode(""));
        
        // Test toString
        assertEquals("PERSONAL", BillingType.PERSONAL.toString());
        assertEquals("BUSINESS", BillingType.BUSINESS.toString());
    }
    
    @Test
    void testItemTypeEnum() {
        // Test enum values
        assertEquals("PHYSICAL", ItemType.PHYSICAL.getCode());
        assertEquals("DIGITAL", ItemType.DIGITAL.getCode());
        assertEquals("SERVICE", ItemType.SERVICE.getCode());
        
        // Test fromCode
        assertEquals(ItemType.PHYSICAL, ItemType.fromCode("PHYSICAL"));
        assertEquals(ItemType.PHYSICAL, ItemType.fromCode("physical"));
        assertEquals(ItemType.DIGITAL, ItemType.fromCode("DIGITAL"));
        assertEquals(ItemType.DIGITAL, ItemType.fromCode("digital"));
        assertEquals(ItemType.SERVICE, ItemType.fromCode("SERVICE"));
        assertEquals(ItemType.SERVICE, ItemType.fromCode("service"));
        
        // Test invalid codes
        assertNull(ItemType.fromCode("INVALID"));
        assertNull(ItemType.fromCode(null));
        assertNull(ItemType.fromCode(""));
        
        // Test toString
        assertEquals("PHYSICAL", ItemType.PHYSICAL.toString());
        assertEquals("DIGITAL", ItemType.DIGITAL.toString());
        assertEquals("SERVICE", ItemType.SERVICE.toString());
    }
}
