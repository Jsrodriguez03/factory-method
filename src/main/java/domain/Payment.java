package main.java.domain;

public abstract class Payment {
    protected double commissionRate;
    protected double extraChargeThreshold;
    protected double extraCharge;

    public Payment(double commissionRate, double extraChargeThreshold, double extraCharge) {
        this.commissionRate = commissionRate;
        this.extraChargeThreshold = extraChargeThreshold;
        this.extraCharge = extraCharge;
    }

    public double pay(double amount) {
        double finalAmount = amount + (amount * commissionRate);
        if (amount > extraChargeThreshold) {
            finalAmount += extraCharge;
        }
        System.out.println(getPaymentMethodMessage());
        return finalAmount;
    }

    protected abstract String getPaymentMethodMessage();
}
