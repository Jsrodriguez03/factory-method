package main.java.controller;

import domain.enums.NotificationType;
import main.java.domain.PaymentType;
import main.java.services.PaymentServices;
import main.java.services.NotificationServices;
import main.java.dto.NotificationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServices paymentService;

    @Autowired
    private NotificationServices notificationService;

    @GetMapping("/types")
    public ResponseEntity<List<String>> getPaymentTypes() {
        return ResponseEntity.ok(PaymentType.getAllPaymentTypes());
    }

    @PostMapping("/pay")
    public ResponseEntity<?> payPayment(
            @RequestParam PaymentType paymentType,
            @RequestParam double amount
    ) {
        double newFinalAmount = paymentService.senderPayment(paymentType, amount);
        return ResponseEntity.ok(newFinalAmount);
    }

    @PostMapping("/notification")
    public ResponseEntity<?> notificationPayment(
            @RequestParam PaymentType paymentType,
            @RequestParam double amount,
            @RequestParam NotificationType notificationType,
            @RequestParam(required = false) String to,  // Parámetros específicos para Email
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String body,
            @RequestParam(required = false) List<String> cc,
            @RequestParam(required = false) List<String> bcc,
            @RequestParam(required = false) List<String> attachments,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String phoneNumber, // Parámetros específicos para SMS/WhatsApp
            @RequestParam(required = false) String senderId,
            @RequestParam(required = false) boolean deliveryReportRequired,
            @RequestParam(required = false) String deviceToken, // Parámetros específicos para Push
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String imageUrl,
            @RequestParam(required = false) String clickAction,
            @RequestParam(required = false) String mediaUrl, // Parámetros específicos para WhatsApp
            @RequestParam(required = false) String caption,
            @RequestParam(required = false) List<String> interactiveButtons,
            @RequestParam(required = false) String language
    ) {
        // Realizar el pago
        double newFinalAmount = paymentService.senderPayment(paymentType, amount);

        // Construir el DTO para la notificación
        NotificationRequestDTO dto = new NotificationRequestDTO();
        dto.setType(notificationType);
        dto.setMessage("Pago realizado con " +paymentType+" exitosamente. con Monto final: $" + newFinalAmount);

        // Asignar los datos recibidos por la URL a los campos del DTO
        switch (notificationType) {
            case EMAIL:
                dto.setTo(to);
                dto.setSubject(subject);
                dto.setBody(body);
                dto.setCc(cc);
                dto.setBcc(bcc);
                dto.setAttachments(attachments);
                dto.setPriority(priority);
                break;
            case SMS:
                dto.setPhoneNumber(phoneNumber);
                dto.setSenderId(senderId);
                dto.setDeliveryReportRequired(deliveryReportRequired);
                break;
            case PUSH:
                dto.setDeviceToken(deviceToken);
                dto.setTitle(title);
                dto.setMessage("Tu pago fue procesado con éxito.");
                dto.setImageUrl(imageUrl);
                dto.setClickAction(clickAction);
                break;
            case WHATSAPP:
                dto.setPhoneNumber(phoneNumber);
                dto.setMessage("Tu pago ha sido realizado exitosamente.");
                dto.setMediaUrl(mediaUrl);
                dto.setCaption(caption);
                dto.setInteractiveButtons(interactiveButtons);
                dto.setLanguage(language);
                break;
            default:
                throw new IllegalArgumentException("Tipo de notificación no soportado");
        }

        // Limpiar los campos no necesarios según el tipo de notificación
        cleanUpDTO(dto, notificationType);

        // Enviar la notificación
        notificationService.sendNotification(dto);

        // Retornar el DTO limpio para que solo se vean los campos relevantes
        return ResponseEntity.ok(dto);
    }

    private void cleanUpDTO(NotificationRequestDTO dto, NotificationType notificationType) {
        // Limpiar campos que no sean necesarios según el tipo de notificación
        if (notificationType != NotificationType.EMAIL) {
            dto.setTo(null);
            dto.setSubject(null);
            dto.setBody(null);
            dto.setCc(null);
            dto.setBcc(null);
            dto.setAttachments(null);
            dto.setPriority(null);
        }
        if (notificationType != NotificationType.SMS) {
            dto.setPhoneNumber(null);
            dto.setSenderId(null);
            dto.setDeliveryReportRequired(false);
        }
        if (notificationType != NotificationType.PUSH) {
            dto.setDeviceToken(null);
            dto.setTitle(null);
            dto.setImageUrl(null);
            dto.setClickAction(null);
        }
        if (notificationType != NotificationType.WHATSAPP) {
            dto.setMediaUrl(null);
            dto.setCaption(null);
            dto.setInteractiveButtons(null);
            dto.setLanguage(null);
        }
    }
}
