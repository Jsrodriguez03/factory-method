package Main.Java.Domain;

public class CreditCardPayment implements Payment {
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
