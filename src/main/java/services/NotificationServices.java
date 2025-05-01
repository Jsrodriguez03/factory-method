package main.java.services;

import domain.notifications.Notification;
import main.java.dto.NotificationRequestDTO;
import org.springframework.stereotype.Service;
import services.NotificationBuilderService;

@Service
public class NotificationServices {

    public void sendNotification(NotificationRequestDTO notificationRequestDTO) {
        // Aquí deberías construir la notificación usando el builder
        Notification notification = new NotificationBuilderService().buildNotification(notificationRequestDTO);

        // Luego, llamar al método notifyUser() para enviar la notificación
        notification.notifyUser();
    }
}
