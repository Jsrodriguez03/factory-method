package domain.builder.Notifications;

import domain.notifications.WhatsappNotification;

import java.util.List;

public class WhatsAppNotificationBuilder {
    private String phoneNumber;
    private String message;
    private String mediaUrl;
    private String caption;
    private List<String> interactiveButtons;
    private String language;

    public static WhatsAppNotificationBuilder builder() {
        return new WhatsAppNotificationBuilder();
    }

    public WhatsAppNotificationBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public WhatsAppNotificationBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public WhatsAppNotificationBuilder setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
        return this;
    }

    public WhatsAppNotificationBuilder setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public WhatsAppNotificationBuilder setInteractiveButtons(List<String> interactiveButtons) {
        this.interactiveButtons = interactiveButtons;
        return this;
    }

    public WhatsAppNotificationBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public WhatsappNotification build() {
        return new WhatsappNotification(phoneNumber, message, mediaUrl, caption, interactiveButtons, language);
    }
}
