package domain.factory;
import main.java.domain.DebitCardPayment;
import main.java.domain.Payment;

public class DebitCardFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new DebitCardPayment();
    }
}
