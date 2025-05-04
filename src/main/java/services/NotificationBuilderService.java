package services;

import domain.builder.Notifications.EmailNotificationBuilder;
import domain.builder.Notifications.PushNotificationBuilder;
import domain.builder.Notifications.SMSNotificationBuilder;
import domain.builder.Notifications.WhatsAppNotificationBuilder;
import domain.notifications.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationBuilderService {

    public Notification buildNotification(main.java.dto.NotificationRequest dto) {
        return switch (dto.getType()) {
            case EMAIL -> EmailNotificationBuilder.builder()
                    .setTo(dto.getTo())
                    .setSubject(dto.getSubject())
                    .setBody(dto.getBody())
                    .setCc(dto.getCc())
                    .setBcc(dto.getBcc())
                    .setAttachments(dto.getAttachments())
                    .setPriority(dto.getPriority())
                    .build();

            case SMS -> SMSNotificationBuilder.builder()
                    .setPhoneNumber(dto.getPhoneNumber())
                    .setMessage(dto.getMessage())
                    .setSenderId(dto.getSenderId())
                    .setDeliveryReportRequired(dto.isDeliveryReportRequired())
                    .setScheduleTime(dto.getScheduleTime())
                    .build();

            case PUSH -> PushNotificationBuilder.builder()
                    .setDeviceToken(dto.getDeviceToken())
                    .setTitle(dto.getTitle())
                    .setMessage(dto.getMessage())
                    .setImageUrl(dto.getImageUrl())
                    .setClickAction(dto.getClickAction())
                    .setPriority(dto.getPriority())
                    .build();

            case WHATSAPP -> WhatsAppNotificationBuilder.builder()
                    .setPhoneNumber(dto.getPhoneNumber())
                    .setMessage(dto.getMessage())
                    .setMediaUrl(dto.getMediaUrl())
                    .setCaption(dto.getCaption())
                    .setInteractiveButtons(dto.getInteractiveButtons())
                    .setLanguage(dto.getLanguage())
                    .build();

            default -> throw new IllegalArgumentException("Tipo de notificación no soportado");
        };
    }
}
