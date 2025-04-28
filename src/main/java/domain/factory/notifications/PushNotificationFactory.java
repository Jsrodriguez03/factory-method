package domain.factory.notifications;

import domain.notifications.Notification;
import domain.notifications.PushNotification;

public class PushNotificationFactory extends NotificationFactory {
    @Override
    protected Notification createNotification() {
        return new PushNotification();
    }
}