package domain.factory.notifications;

import domain.notifications.WhatsappNotification;
import domain.notifications.Notification;

public class WhatsAppNotificationFactory extends NotificationFactory {
    @Override
    protected Notification createNotification() {
        return new WhatsappNotification();
    }
}