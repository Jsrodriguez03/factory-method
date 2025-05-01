package domain.notifications;

import java.util.List;

public class WhatsappNotification implements Notification {
    private final String phoneNumber;
    private final String message;
    private final String mediaUrl;
    private final String caption;
    private final List<String> interactiveButtons;
    private final String language;

    public WhatsappNotification(String phoneNumber, String message,
                                String mediaUrl, String caption,
                                List<String> interactiveButtons, String language) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.mediaUrl = mediaUrl;
        this.caption = caption;
        this.interactiveButtons = interactiveButtons;
        this.language = language;
    }

    @Override
    public void notifyUser() {
        System.out.println("📱 WhatsApp a: " + phoneNumber);
        System.out.println("Mensaje: " + message);
        if (mediaUrl != null) System.out.println("Media: " + mediaUrl);
        if (caption != null) System.out.println("Descripción: " + caption);
        if (interactiveButtons != null && !interactiveButtons.isEmpty()) {
            System.out.println("Botones: " + interactiveButtons);
        }
        System.out.println("Idioma: " + language);
    }
}
