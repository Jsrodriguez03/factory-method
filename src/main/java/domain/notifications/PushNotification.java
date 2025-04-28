package domain.notifications;

public class PushNotification implements Notification {
    @Override
    public void notifyUser(String message) {
        System.out.println("Enviando Push Notification: " + message);
    }
}
