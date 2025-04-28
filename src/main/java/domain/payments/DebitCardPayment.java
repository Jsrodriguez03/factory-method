package domain.payments;

public class DebitCardPayment extends Payment {
    public DebitCardPayment() {
        super(0.01, 500, 5);
    }

    @Override
    protected String getPaymentMethodMessage() {
        return "Procesando pago con tarjeta de débito";
    }
}
