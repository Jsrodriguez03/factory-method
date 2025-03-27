package domain.factory;
import main.java.domain.Payment;

public abstract class PaymentFactory {

    public Payment getPayment(){
        return createPayment();
    }

    public abstract Payment createPayment();

}

