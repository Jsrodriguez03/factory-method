package domain.factory.payments;
import domain.payments.DebitCardPayment;
import domain.payments.Payment;

public class DebitCardFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new DebitCardPayment();
    }
}
