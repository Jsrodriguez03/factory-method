package domain.payments;

public class CreditCardPayment extends Payment {
    public CreditCardPayment() {
        super(0.03, 1000, 10);
    }

    @Override
    protected String getPaymentMethodMessage() {
        return "Procesando pago con tarjeta de crédito";
    }
}
