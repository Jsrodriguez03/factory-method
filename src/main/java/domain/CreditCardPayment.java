package main.java.domain;

public class CreditCardPayment implements main.java.domain.Payment {
    double finalAmount = 0.0;

    @Override
    public double pay(double amount) {
        double comissionRate = 0.03;
        finalAmount = amount + (amount * comissionRate);
        System.out.println("Procesando pago con tarjeta de credito");

        if (amount > 1000) {
            finalAmount += 10;
        }
        return(finalAmount);
    }
}
