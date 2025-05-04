package domain.builder.Notifications;

import domain.notifications.PushNotification;

public class PushNotificationBuilder {
    private String deviceToken;
    private String title;
    private String message;
    private String imageUrl;
    private String clickAction;
    private String priority;

    public static PushNotificationBuilder builder() {
        return new PushNotificationBuilder();
    }

    public PushNotificationBuilder setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

    public PushNotificationBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PushNotificationBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public PushNotificationBuilder setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PushNotificationBuilder setClickAction(String clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    public PushNotificationBuilder setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public PushNotification build() {
        return new PushNotification(deviceToken, title, message, imageUrl, clickAction, priority);
    }
}
