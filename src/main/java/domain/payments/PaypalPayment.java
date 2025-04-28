package domain.payments;

public class PaypalPayment extends Payment {
    public PaypalPayment() {
        super(0.02, 750, 7);
    }

    @Override
    protected String getPaymentMethodMessage() {
        return "Procesando pago con PayPal";
    }
}
