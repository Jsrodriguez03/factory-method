package domain.factory.payments;
import domain.payments.Payment;

public abstract class PaymentFactory {

    public Payment getPayment(){
        return createPayment();
    }

    public abstract Payment createPayment();

}

