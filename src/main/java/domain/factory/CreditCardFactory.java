package domain.factory;
import main.java.domain.CreditCardPayment;
import main.java.domain.Payment;

public class CreditCardFactory extends PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
