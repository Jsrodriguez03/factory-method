package domain.factory.notifications;

import domain.notifications.Notification;
import domain.notifications.SMSNotification;

public class SMSNotificationFactory extends NotificationFactory {
    @Override
    protected Notification createNotification() {
        return new SMSNotification();
    }
}
