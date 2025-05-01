package main.java.config;

import domain.factory.payments.CreditCardFactory;
import domain.factory.payments.DebitCardFactory;
import domain.factory.payments.PaypalFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {

    @Bean
    public CreditCardFactory creditCardFactory() {
        return new CreditCardFactory();
    }

    @Bean
    public DebitCardFactory debitCardFactory() {
        return new DebitCardFactory();
    }

    @Bean
    public PaypalFactory paypalFactory() {
        return new PaypalFactory();
    }
}
