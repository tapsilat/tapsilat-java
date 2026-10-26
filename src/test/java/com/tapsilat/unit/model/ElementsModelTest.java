package com.tapsilat.unit.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tapsilat.constants.ElementsConstants;
import com.tapsilat.model.elements.ElementsCardSummary;
import com.tapsilat.model.elements.ElementsSession;
import com.tapsilat.model.elements.ElementsSessionWithSecret;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Elements session models.
 */
class ElementsModelTest {

    private static final String CLIENT_SECRET = "es_test_1_secret_x";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static ElementsSessionWithSecret sessionWithSecret() {
        ElementsSessionWithSecret session = new ElementsSessionWithSecret();
        session.setId("es_test_1");
        session.setStatus(ElementsConstants.STATUS_REQUIRES_PAYMENT_METHOD);
        session.setClientSecret(CLIENT_SECRET);
        session.setElementsBaseUrl("https://checkout.tapsilat.dev");
        return session;
    }

    @Test
    void testToStringRedactsClientSecret() {
        ElementsSessionWithSecret session = sessionWithSecret();

        String printed = session.toString();

        assertFalse(printed.contains(CLIENT_SECRET), printed);
        assertTrue(printed.contains("clientSecret=[redacted]"), printed);
        assertTrue(printed.contains("es_test_1"), printed);
        assertTrue(printed.contains("https://checkout.tapsilat.dev"), printed);
        assertFalse(String.valueOf(session).contains(CLIENT_SECRET));
        assertFalse(("session: " + session).contains(CLIENT_SECRET));
    }

    @Test
    void testToStringWithoutClientSecret() {
        ElementsSessionWithSecret session = new ElementsSessionWithSecret();
        assertTrue(session.toString().contains("clientSecret=null"));
    }

    @Test
    void testJsonKeepsClientSecret() throws Exception {
        String json = objectMapper.writeValueAsString(sessionWithSecret());

        @SuppressWarnings("unchecked")
        Map<String, Object> map = objectMapper.readValue(json, Map.class);
        assertEquals(CLIENT_SECRET, map.get("client_secret"));
        assertEquals("https://checkout.tapsilat.dev", map.get("elements_base_url"));
        assertEquals("es_test_1", map.get("id"));
        assertFalse(map.containsKey("card"), "unset fields are omitted");
    }

    @Test
    void testSessionToStringIncludesCard() {
        ElementsCardSummary card = new ElementsCardSummary();
        card.setBrand(ElementsConstants.BRAND_MASTERCARD);
        card.setLast4("4444");
        ElementsSession session = new ElementsSession();
        session.setId("es_test_2");
        session.setCard(card);

        String printed = session.toString();

        assertTrue(printed.startsWith("ElementsSession{"), printed);
        assertTrue(printed.contains("es_test_2"), printed);
        assertTrue(printed.contains("last4='4444'"), printed);
    }
}
