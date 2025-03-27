package domain.factory;
import main.java.domain.PaypalPayment;
import main.java.domain.Payment;

public class PaypalFactory extends PaymentFactory{
    @Override
    public Payment createPayment(){
        return new PaypalPayment();
    }
}
