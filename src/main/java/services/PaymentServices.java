package main.java.services;

import domain.enums.NotificationType;
import domain.factory.payments.CreditCardFactory;
import domain.factory.payments.DebitCardFactory;
import domain.factory.payments.PaymentFactory;
import domain.factory.payments.PaypalFactory;
import main.java.domain.PaymentType;
import domain.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServices {

    private PaymentFactory paymentFactory;

    @Autowired
    private main.java.services.NotificationServices notificationService;

    public PaymentServices() {}

    public double senderPayment(PaymentType paymentType, double amount, NotificationType notificationType) {
        configurationFactory(paymentType);
        Payment payment = paymentFactory.getPayment();
        double finalAmount = payment.pay(amount);

        // Enviar notificación
        String message = "Pago realizado exitosamente. Monto final: $" + finalAmount;
        notificationService.sendNotification(notificationType, message);

        return finalAmount;
    }

    private void configurationFactory(PaymentType paymentType) {
        switch (paymentType) {
            case CREDIT_CARD -> paymentFactory = new CreditCardFactory();
            case DEBIT_CARD -> paymentFactory = new DebitCardFactory();
            case PAYPAL -> paymentFactory = new PaypalFactory();
            default -> throw new IllegalArgumentException("Método de pago no soportado");
        }
    }
}
