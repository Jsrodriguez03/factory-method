package main.java.controller;

import domain.DTOs.ReportConfig;
import domain.builder.Report.PDFReportBuilder;
import domain.builder.Report.ReportDirector;
import domain.enums.NotificationType;
import main.java.domain.PaymentType;
import main.java.services.PaymentServices;
import main.java.services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> notificationPayment(@RequestBody main.java.dto.NotificationRequest dto) {
        // Calcula el nuevo monto
        double newFinalAmount = paymentService.senderPayment(dto.getPaymentType(), dto.getAmount());

        // Construye el mensaje base
        dto.setMessage("Pago realizado con " + dto.getPaymentType() + " exitosamente. con Monto final: $" + newFinalAmount);

        // Limpia según tipo
        cleanUpDTO(dto, dto.getType());

        // Envía la notificación
        notificationService.sendNotification(dto);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/reporte-pago")
    public ResponseEntity<byte[]> generarReporte(@RequestBody ReportConfig config) {
        PDFReportBuilder builder = new PDFReportBuilder();
        ReportDirector director = new ReportDirector(builder);
        byte[] pdf = director.constructReport(config);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("reporte.pdf").build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


    private void cleanUpDTO(main.java.dto.NotificationRequest dto, NotificationType notificationType) {
        // Limpiar campos que no sean necesarios según el tipo de notificación
        if (notificationType != NotificationType.EMAIL) {
            dto.setTo(null);
            dto.setSubject(null);
            dto.setBody(null);
            dto.setCc(null);
            dto.setBcc(null);
            dto.setAttachments(null);
        }
        if (notificationType != NotificationType.SMS) {
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
