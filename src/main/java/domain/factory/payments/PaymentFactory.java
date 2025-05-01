package domain.factory.payments;
import domain.payments.Payment;

import org.springframework.stereotype.Component;

@Component
public abstract class PaymentFactory {

    public Payment getPayment(){
        return createPayment();
    }

    public abstract Payment createPayment();

}

