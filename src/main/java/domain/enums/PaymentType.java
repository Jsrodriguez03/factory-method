package main.java.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PaymentType {
    CREDIT_CARD,
    DEBIT_CARD,
    PAYPAL;

    // Método para convertir los valores del enum a una lista de strings
    public static List<String> getAllPaymentTypes() {
        return Arrays.stream(PaymentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
