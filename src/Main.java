public class Main {
    public static void main(String[] args) {
        // Crear una instancia de PaymentProcessor
        PaymentProcessor1 processor = new PaymentProcessor1();

        // Llamar a processPayment con diferentes métodos de pago
        try {
            double creditCardPayment = processor.processPayment("CREDIT_CARD", 1200);
            System.out.println("Monto final (CREDIT_CARD): " + creditCardPayment);

            double debitCardPayment = processor.processPayment("DEBIT_CARD", 600);
            System.out.println("Monto final (DEBIT_CARD): " + debitCardPayment);

            double paypalPayment = processor.processPayment("PayPal", 800);
            System.out.println("Monto final (PayPal): " + paypalPayment);

            // Probar un método de pago no soportado
            double unsupportedPayment = processor.processPayment("BITCOIN", 500);
            System.out.println("Monto final (BITCOIN): " + unsupportedPayment);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
