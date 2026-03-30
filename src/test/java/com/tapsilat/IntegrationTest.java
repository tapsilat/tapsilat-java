package com.tapsilat;

import com.tapsilat.config.TapsilatConfig;
import com.tapsilat.model.order.OrderRequest;
import com.tapsilat.model.order.OrderResponse;
import com.tapsilat.model.common.Buyer;
import com.tapsilat.model.common.BillingAddress;
import com.tapsilat.model.common.BasketItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    private TapsilatClient client;
    private String token;

    @BeforeEach
    public void setUp() {
        token = System.getenv("TAPSILAT_API_KEY");
        if (token == null) {
            System.out.println("Warning: TAPSILAT_API_KEY not set. Skipping setup.");
            return;
        }

        TapsilatConfig config = new TapsilatConfig();
        config.setBaseUrl("https://panel.tapsilat.dev/api/v1");
        config.setBearerToken(token);

        client = new TapsilatClient(config);
    }

    @Test
    public void testCreateOrder() throws Exception {
        if (token == null) {
            System.out.println("Skipping testCreateOrder: No API Key");
            return;
        }

        OrderRequest order = new OrderRequest();
        order.setAmount(BigDecimal.valueOf(10.0));
        order.setCurrency("TRY");
        order.setLocale("tr");

        Buyer buyer = new Buyer();
        buyer.setId("123456");
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setEmail("john@doe.com");
        buyer.setGsmNumber("5321234567");
        buyer.setIdentityNumber("12345678901");
        buyer.setCity("Istanbul");
        buyer.setCountry("Turkey");
        buyer.setZipCode("34732");
        buyer.setIp("127.0.0.1");
        order.setBuyer(buyer);

        BillingAddress billing = new BillingAddress();
        billing.setContactName("John Doe");
        billing.setCity("Istanbul");
        billing.setCountry("Turkey");
        billing.setAddress("Test Address");
        billing.setZipCode("34732");
        order.setBillingAddress(billing);

        List<BasketItem> items = new ArrayList<>();
        BasketItem item1 = new BasketItem();
        item1.setId("1");
        item1.setName("Item 1");
        item1.setPrice(5.0);
        item1.setItemType("PHYSICAL");
        items.add(item1);

        BasketItem item2 = new BasketItem();
        item2.setId("2");
        item2.setName("Item 2");
        item2.setPrice(5.0);
        item2.setItemType("PHYSICAL");
        items.add(item2);

        order.setBasketItems(items);

        OrderResponse response = client.orders().create(order);
        assertNotNull(response);
        assertNotNull(response.getReferenceId());
        System.out.println("Order Created: " + response.getReferenceId());
    }
}
