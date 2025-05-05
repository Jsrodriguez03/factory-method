package main.java.controller;


import com.itextpdf.text.DocumentException;
import domain.builder.Report.PDFReportBuilder;
import domain.builder.Report.ReportDirector;
import domain.enums.NotificationType;
import main.java.domain.PaymentType;
import main.java.services.PaymentServices;
import main.java.services.NotificationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<byte[]> generarPDF(@RequestBody domain.dto.PDFReportRequest request) throws DocumentException, IOException {
        // 1. Crear el prototipo con valores base
        PDFReportBuilder prototype = new PDFReportBuilder();
        prototype.setTheme("light"); // Valor base
        prototype.setFormat("A4");   // Valor base
        prototype.setIncludeLogo(false);
        prototype.setIncludePaymentDetails(false);
        prototype.setIncludeUserInfo(false);
        prototype.setIncludeTimestamp(false);

        // 2. Clonar el prototipo para personalizar el builder según el request
        PDFReportBuilder builder = prototype.clone();
        builder.reset();

        // 3. Asignar valores personalizados desde el request (manteniendo tu diseño actual)
        builder.setPaymentAmount(request.getPaymentAmount());
        builder.setPaymentType(request.getPaymentType());
        builder.setPaymentTax(request.getPaymentTax());
        builder.setPaymentTotal(request.getPaymentTotal());

        builder.setIncludeLogo(request.isIncludeLogo());
        builder.setIncludePaymentDetails(request.isIncludePaymentDetails());
        builder.setIncludeUserInfo(request.isIncludeUserInfo());
        builder.setIncludeTimestamp(request.isIncludeTimestamp());

        builder.setTheme(request.getTheme());
        builder.setFormat(request.getFormat());
        builder.setFooterMessage(request.getFooterMessage());
        builder.setTitle(request.getTitle());

        // 4. Construir el reporte usando tu lógica actual
        ReportDirector director = new ReportDirector(builder);
        director.construirReporte(request); // <- tu método original

        byte[] pdfBytes = builder.build();

        // 5. Devolver el PDF
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Factura.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
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
