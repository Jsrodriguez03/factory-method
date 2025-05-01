package domain.factory.notifications;

import domain.notifications.Notification;

public abstract class NotificationFactory {
    public abstract Notification createNotification();
}
