package domain.factory.payments;
import domain.payments.PaypalPayment;
import domain.payments.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaypalFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new PaypalPayment();
    }
}
