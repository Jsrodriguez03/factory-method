package main.java.services;

import domain.factory.payments.CreditCardFactory;
import domain.factory.payments.DebitCardFactory;
import domain.factory.payments.PaymentFactory;
import domain.factory.payments.PaypalFactory;
import main.java.domain.PaymentType;
import domain.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private final PaymentFactory creditCardFactory;
    private final PaymentFactory debitCardFactory;
    private final PaymentFactory paypalFactory;

    @Autowired
    public PaymentServices(CreditCardFactory creditCardFactory,
                           DebitCardFactory debitCardFactory,
                           PaypalFactory paypalFactory) {
        this.creditCardFactory = creditCardFactory;
        this.debitCardFactory = debitCardFactory;
        this.paypalFactory = paypalFactory;
    }

    public double senderPayment(PaymentType paymentType, double amount) {
        PaymentFactory factory = getPaymentFactory(paymentType);
        Payment payment = factory.getPayment();
        return payment.pay(amount);  // Retorna el monto final después del pago
    }

    private PaymentFactory getPaymentFactory(PaymentType paymentType) {
        switch (paymentType) {
            case CREDIT_CARD:
                return creditCardFactory;
            case DEBIT_CARD:
                return debitCardFactory;
            case PAYPAL:
                return paypalFactory;
            default:
                throw new IllegalArgumentException("Método de pago no soportado");
        }
    }
}
