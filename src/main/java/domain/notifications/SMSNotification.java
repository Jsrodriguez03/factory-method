package domain.notifications;

import java.time.LocalDateTime;

public class SMSNotification implements Notification {
    private final String phoneNumber;
    private final String message;
    private final String senderId;
    private final boolean deliveryReportRequired;
    private final LocalDateTime scheduleTime;

    public SMSNotification(String phoneNumber, String message, String senderId,
                           boolean deliveryReportRequired, LocalDateTime scheduleTime) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.senderId = senderId;
        this.deliveryReportRequired = deliveryReportRequired;
        this.scheduleTime = scheduleTime;
    }

    @Override
    public void notifyUser() {
        System.out.println("📩 SMS a: " + phoneNumber);
        System.out.println("Mensaje: " + message);
        if (senderId != null) System.out.println("Remitente: " + senderId);
        System.out.println("Requiere reporte de entrega: " + deliveryReportRequired);
        if (scheduleTime != null) System.out.println("Envío programado para: " + scheduleTime);
    }
}
