package Main.Java.Services;

import Main.Java.Domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {
    private Payment payment;

    public PaymentServices() {}

    public double payPayment(String paymentType, double amount){
        if (paymentType.equals("CREDIT_CARD")) {
            payment = new CreditCardPayment();
        }

        else if (paymentType.equals("DEBIT_CARD")) {
            payment = new DebitCardPayment();
        }

        else if (paymentType.equals("PayPal")) {
            payment = new PaypalPayment();
        }
        else {
            throw new IllegalArgumentException("Método de pago no soportado");
        }

        return payment.pay(amount);
    }
}
