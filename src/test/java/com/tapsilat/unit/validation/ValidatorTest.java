package com.tapsilat.unit.validation;

import com.tapsilat.model.common.Buyer;
import com.tapsilat.model.order.OrderCreateRequest;
import com.tapsilat.validation.OrderRequestValidator;
import com.tapsilat.validation.ValidationException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    // ==================== GSM Number Cleaning ====================

    @Test
    void testCleanGsmNumber_InternationalPlusFormat() {
        List<String> errors = new ArrayList<>();
        String result = OrderRequestValidator.cleanGsmNumber("+905551234567", errors);
        assertEquals("+905551234567", result);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testCleanGsmNumber_InternationalDoubleZeroFormat() {
        List<String> errors = new ArrayList<>();
        String result = OrderRequestValidator.cleanGsmNumber("00905551234567", errors);
        assertEquals("00905551234567", result);
        assertTrue(errors.isEmpty());
    }



    @Test
    void testCleanGsmNumber_RemovesFormattingCharacters() {
        List<String> errors = new ArrayList<>();
        String result = OrderRequestValidator.cleanGsmNumber("+90 555 123-45(67)", errors);
        assertEquals("+905551234567", result);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testCleanGsmNumber_StripsPlusSignsNotAtStart() {
        List<String> errors = new ArrayList<>();
        String result = OrderRequestValidator.cleanGsmNumber("12+34+567", errors);
        assertEquals("1234567", result);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testCleanGsmNumber_TooShort() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.cleanGsmNumber("123", errors);
        assertFalse(errors.isEmpty());
        assertTrue(errors.get(0).contains("too short"));
    }

    @Test
    void testCleanGsmNumber_NullReturnsNull() {
        List<String> errors = new ArrayList<>();
        assertNull(OrderRequestValidator.cleanGsmNumber(null, errors));
        assertTrue(errors.isEmpty());
    }

    @Test
    void testCleanGsmNumber_EmptyReturnsEmpty() {
        List<String> errors = new ArrayList<>();
        assertEquals("", OrderRequestValidator.cleanGsmNumber("", errors));
        assertTrue(errors.isEmpty());
    }

    // ==================== Installment Validation ====================

    @Test
    void testValidateInstallmentsList_ValidValues() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(Arrays.asList(1, 3, 6, 12), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateInstallmentsList_SingleValid() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(Arrays.asList(1), errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateInstallmentsList_ValueTooLow() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(Arrays.asList(0, 2, 3), errors);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).contains("invalid"));
    }

    @Test
    void testValidateInstallmentsList_ValueTooHigh() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(Arrays.asList(1, 13, 3), errors);
        assertEquals(1, errors.size());
        assertTrue(errors.get(0).contains("invalid"));
    }

    @Test
    void testValidateInstallmentsList_MultipleBadValues() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(Arrays.asList(0, 13), errors);
        assertEquals(2, errors.size());
    }

    @Test
    void testValidateInstallmentsList_NullIsNoop() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(null, errors);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidateInstallmentsList_EmptyIsNoop() {
        List<String> errors = new ArrayList<>();
        OrderRequestValidator.validateInstallmentsList(new ArrayList<>(), errors);
        assertTrue(errors.isEmpty());
    }

    // ==================== Full Validate ====================

    @Test
    void testValidate_NullRequestThrows() {
        assertThrows(ValidationException.class, () -> OrderRequestValidator.validate(null));
    }

    @Test
    void testValidate_ValidRequest() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        List<String> errors = OrderRequestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidate_MissingAmount() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        List<String> errors = OrderRequestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("Amount")));
    }

    @Test
    void testValidate_ZeroAmount() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(BigDecimal.ZERO);
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        List<String> errors = OrderRequestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("greater than zero")));
    }

    @Test
    void testValidate_MissingCurrency() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "test@example.com"));

        List<String> errors = OrderRequestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("Currency")));
    }

    @Test
    void testValidate_MissingBuyer() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setCurrency("TRY");
        request.setLocale("tr");

        List<String> errors = OrderRequestValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testValidate_InvalidEmail() {
        OrderCreateRequest request = new OrderCreateRequest();
        request.setAmount(new BigDecimal("100"));
        request.setCurrency("TRY");
        request.setLocale("tr");
        request.setBuyer(new Buyer("John", "Doe", "bad-email"));

        List<String> errors = OrderRequestValidator.validate(request);
        assertFalse(errors.isEmpty());
        assertTrue(errors.stream().anyMatch(e -> e.contains("email")));
    }

    @Test
    void testValidateOrThrow_InvalidThrows() {
        OrderCreateRequest request = new OrderCreateRequest();
        // Missing all required fields
        assertThrows(ValidationException.class, () -> OrderRequestValidator.validateOrThrow(request));
    }
}
