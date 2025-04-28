package main.java.services;

import domain.enums.NotificationType;
import domain.factory.notifications.*;
import domain.notifications.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices {

    public void sendNotification(NotificationType notificationType, String message) {
        NotificationFactory factory = getFactory(notificationType);
        Notification notification = factory.getNotification();
        notification.notifyUser(message);
    }

    private NotificationFactory getFactory(NotificationType notificationType) {
        return switch (notificationType) {
            case EMAIL -> new EmailNotificationFactory();
            case SMS -> new SMSNotificationFactory();
            case PUSH -> new PushNotificationFactory();
            case WHATSAPP -> new WhatsAppNotificationFactory();
            default -> throw new IllegalArgumentException("Tipo de notificación no soportado");
        };
    }
}