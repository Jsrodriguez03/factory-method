package domain.factory.notifications;

import domain.enums.NotificationType;
import domain.notifications.*;

public abstract class NotificationFactory {
    public Notification getNotification() {
        return createNotification();
    }

    protected abstract Notification createNotification();
}
