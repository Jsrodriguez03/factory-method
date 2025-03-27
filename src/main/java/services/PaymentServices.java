package main.java.services;

import domain.factory.CreditCardFactory;
import domain.factory.DebitCardFactory;
import domain.factory.PaymentFactory;
import domain.factory.PaypalFactory;
import main.java.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {
    private PaymentFactory paymentFactory;

    public PaymentServices() {}

    public double senderPayment(String paymentType, double amount){
        configurationFactory(paymentType);
        Payment payment = paymentFactory.getPayment();
        return payment.pay(amount);
    }

    private void configurationFactory(String paymentType){
        if(paymentType.equals("CREDIT_CARD")){
            paymentFactory = new CreditCardFactory();
        } else if (paymentType.equals("DEBIT_CARD")) {
            paymentFactory = new DebitCardFactory();
        }else if(paymentType.equals("PayPal")){
            paymentFactory = new PaypalFactory();
        }else{
            throw new IllegalArgumentException("Método de pago no soportado");
        }
    }
}
