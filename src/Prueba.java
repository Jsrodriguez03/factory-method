public class Prueba {

    public class PaymentProcessor {
        public double processPayment (String paymentType, double amount) {
            double finalAmount = 0.0;

            if (paymentType.equals("CREDIT_CARD")) {
                double comissionRate = 0.03;
                finalAmount = amount + (amount * comissionRate);
                System.out.println("Procesando pago con tarjeta de credito");

                if (amount > 1000) {
                    finalAmount += 10;
                }
            }

            else if (paymentType.equals("DEBIT_CARD")) {
                double comissionRate = 0.01;
                finalAmount = amount + (amount * comissionRate);
                System.out.println("Procesando pago con tarjeta de debito");

                if (amount > 500) {
                    finalAmount += 5;
                }
            }

            else if (paymentType.equals("PayPal")) {
                double comissionRate = 0.02;
                finalAmount = amount + (amount * comissionRate);
                System.out.println("Procesando pago con PayPal");

                if (amount > 750) {
                    finalAmount += 7;
                }
            }
            else {
                throw new IllegalArgumentException("Método de pago no soportado");
            }

            return finalAmount;

        }



    }

}
