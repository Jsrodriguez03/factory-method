package main.java.domain;

public class PaypalPayment implements Payment {
    double finalAmount = 0.0;

    @Override
    public double pay(double amount) {
        double comissionRate = 0.02;
        finalAmount = amount + (amount * comissionRate);
        System.out.println("Procesando pago con PayPal");

        if (amount > 750) {
            finalAmount += 7;
        }

        return(finalAmount);
    }
}
