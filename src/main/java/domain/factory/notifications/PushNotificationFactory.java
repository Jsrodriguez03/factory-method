package domain.factory.notifications;

import domain.builder.Notifications.PushNotificationBuilder;
import domain.notifications.Notification;

public class PushNotificationFactory extends NotificationFactory {

    private final String deviceToken;
    private final String title;
    private final String message;
    private final String imageUrl;
    private final String clickAction;
    private final String priority;

    public PushNotificationFactory(String deviceToken, String title, String message,
                                   String imageUrl, String clickAction, String priority) {
        this.deviceToken = deviceToken;
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.clickAction = clickAction;
        this.priority = priority;
    }

    @Override
    public Notification createNotification() {
        return new PushNotificationBuilder()
                .setDeviceToken(deviceToken)
                .setTitle(title)
                .setMessage(message)
                .setImageUrl(imageUrl)
                .setClickAction(clickAction)
                .setPriority(priority)
                .build();
    }
}
