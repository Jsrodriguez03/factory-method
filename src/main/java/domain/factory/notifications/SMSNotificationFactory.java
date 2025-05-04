package domain.factory.notifications;

import domain.builder.Notifications.SMSNotificationBuilder;
import domain.notifications.Notification;

import java.time.LocalDateTime;

public class SMSNotificationFactory extends NotificationFactory {

    private final String phoneNumber;
    private final String message;
    private final String senderId;
    private final boolean deliveryReportRequired;
    private final LocalDateTime scheduleTime;

    public SMSNotificationFactory(String phoneNumber, String message, String senderId,
                                  boolean deliveryReportRequired, LocalDateTime scheduleTime) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.senderId = senderId;
        this.deliveryReportRequired = deliveryReportRequired;
        this.scheduleTime = scheduleTime;
    }

    @Override
    public Notification createNotification() {
        return new SMSNotificationBuilder()
                .setPhoneNumber(phoneNumber)
                .setMessage(message)
                .setSenderId(senderId)
                .setDeliveryReportRequired(deliveryReportRequired)
                .setScheduleTime(scheduleTime)
                .build();
    }
}
