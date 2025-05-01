package domain.notifications;

public class PushNotification implements Notification {
    private final String deviceToken;
    private final String title;
    private final String message;
    private final String imageUrl;
    private final String clickAction;
    private final String priority;

    public PushNotification(String deviceToken, String title, String message,
                            String imageUrl, String clickAction, String priority) {
        this.deviceToken = deviceToken;
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.clickAction = clickAction;
        this.priority = priority;
    }

    @Override
    public void notifyUser() {
        System.out.println("🔔 Notificación PUSH a dispositivo: " + deviceToken);
        System.out.println("Título: " + title);
        System.out.println("Mensaje: " + message);
        if (imageUrl != null) System.out.println("Imagen: " + imageUrl);
        if (clickAction != null) System.out.println("Acción al clic: " + clickAction);
        System.out.println("Prioridad: " + priority);
    }
}
