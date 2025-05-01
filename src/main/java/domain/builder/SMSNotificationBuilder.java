package domain.builder;

import domain.notifications.SMSNotification;

import java.time.LocalDateTime;

public class SMSNotificationBuilder {
    private String phoneNumber;
    private String message;
    private String senderId;
    private boolean deliveryReportRequired;
    private LocalDateTime scheduleTime;

    public static SMSNotificationBuilder builder() {
        return new SMSNotificationBuilder();
    }

    public SMSNotificationBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public SMSNotificationBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public SMSNotificationBuilder setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public SMSNotificationBuilder setDeliveryReportRequired(boolean deliveryReportRequired) {
        this.deliveryReportRequired = deliveryReportRequired;
        return this;
    }

    public SMSNotificationBuilder setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
        return this;
    }

    public SMSNotification build() {
        return new SMSNotification(phoneNumber, message, senderId, deliveryReportRequired, scheduleTime);
    }
}
