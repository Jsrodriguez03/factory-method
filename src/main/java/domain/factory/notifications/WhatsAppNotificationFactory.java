package domain.factory.notifications;

import domain.builder.WhatsAppNotificationBuilder;
import domain.notifications.Notification;
import domain.notifications.WhatsappNotification;

import java.util.List;

public class WhatsAppNotificationFactory extends NotificationFactory {

    private final String phoneNumber;
    private final String message;
    private final String mediaUrl;
    private final String caption;
    private final List<String> interactiveButtons;
    private final String language;

    public WhatsAppNotificationFactory(String phoneNumber, String message, String mediaUrl,
                                       String caption, List<String> interactiveButtons, String language) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.mediaUrl = mediaUrl;
        this.caption = caption;
        this.interactiveButtons = interactiveButtons;
        this.language = language;
    }

    @Override
    public Notification createNotification() {
        return new WhatsAppNotificationBuilder()
                .setPhoneNumber(phoneNumber)
                .setMessage(message)
                .setMediaUrl(mediaUrl)
                .setCaption(caption)
                .setInteractiveButtons(interactiveButtons)
                .setLanguage(language)
                .build();
    }
}
