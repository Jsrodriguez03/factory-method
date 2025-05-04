package main.java.dto;

import domain.enums.NotificationType;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // Excluir campos con valor null
public class NotificationRequestDTO {
    private main.java.domain.PaymentType paymentType;
    private double amount;
    @Getter
    @Setter
    private NotificationType type;

    // Comunes
    private String phoneNumber;
    private String message;

    // Email
    private String to;
    private String subject;
    private String body;
    private List<String> cc;
    private List<String> bcc;
    private List<String> attachments;
    private String priority;

    // SMS
    private String senderId;
    private boolean deliveryReportRequired;
    private LocalDateTime scheduleTime;

    // Push
    private String deviceToken;
    private String title;
    private String imageUrl;
    private String clickAction;

    // WhatsApp
    private String mediaUrl;
    private String caption;
    private List<String> interactiveButtons;
    private String language;
}
