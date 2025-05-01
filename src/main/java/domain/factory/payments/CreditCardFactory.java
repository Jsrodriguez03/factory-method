package domain.factory.payments;
import domain.payments.CreditCardPayment;
import domain.payments.Payment;
import org.springframework.stereotype.Component;

@Component
public class CreditCardFactory extends PaymentFactory{
    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}
