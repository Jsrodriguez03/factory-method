package domain.factory.notifications;

import domain.notifications.EmailNotification;
import domain.notifications.Notification;

public class EmailNotificationFactory extends NotificationFactory {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}
