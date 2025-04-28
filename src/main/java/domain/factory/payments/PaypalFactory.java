package domain.factory.payments;
import domain.payments.PaypalPayment;
import domain.payments.Payment;

public class PaypalFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new PaypalPayment();
    }
}
