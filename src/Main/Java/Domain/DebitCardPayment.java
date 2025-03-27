package Main.Java.Domain;

public class DebitCardPayment implements Payment {
    double finalAmount = 0.0;

    @Override
    public double pay(double amount) {
        double comissionRate = 0.01;
        finalAmount = amount + (amount * comissionRate);
        System.out.println("Procesando pago con tarjeta de debito");

        if (amount > 500) {
            finalAmount += 5;
        }

        return(finalAmount);
    }
}
