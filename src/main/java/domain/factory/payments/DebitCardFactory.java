package domain.factory.payments;
import domain.payments.DebitCardPayment;
import domain.payments.Payment;
import org.springframework.stereotype.Component;

@Component
public class DebitCardFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new DebitCardPayment();
    }
}
