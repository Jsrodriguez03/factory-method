package domain.builder.Report;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;


import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PDFReportBuilder implements ReportBuilder {

    private Document document;
    private ByteArrayOutputStream outputStream;
    private PdfWriter writer;

    private boolean includeLogo;
    private String title;
    private boolean includePaymentDetails;
    private boolean includeUserInfo;
    private String theme;
    private boolean includeTimestamp;
    private String footerMessage;
    private String format;

    @Override
    public void reset() {
        outputStream = new ByteArrayOutputStream();
        Rectangle pageSize = PageSize.A4;

        if ("LETTER".equalsIgnoreCase(format)) {
            pageSize = PageSize.LETTER;
        }

        document = new Document(pageSize);
        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        document.addTitle(title);
        try {
            Paragraph p = new Paragraph(title, new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(20);
            document.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setLogo(boolean includeLogo) {
        this.includeLogo = includeLogo;
        if (includeLogo) {
            try {
                Image img = Image.getInstance("classpath:logo.png");
                img.scaleToFit(100, 100);
                img.setAlignment(Image.ALIGN_RIGHT);
                document.add(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setPaymentDetails(boolean includePaymentDetails) {
        this.includePaymentDetails = includePaymentDetails;
        if (includePaymentDetails) {
            try {
                Paragraph p = new Paragraph("Detalles del Pago:\n- Tipo: Tarjeta de Crédito\n- Número: ****-****-****-1234",
                        new Font(Font.FontFamily.HELVETICA, 12));
                p.setSpacingAfter(15);
                document.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setUserInfo(boolean includeUserInfo) {
        this.includeUserInfo = includeUserInfo;
        if (includeUserInfo) {
            try {
                Paragraph p = new Paragraph("Usuario:\n- Nombre: Juan Pérez\n- Email: juan@example.com",
                        new Font(Font.FontFamily.HELVETICA, 12));
                p.setSpacingAfter(15);
                document.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setTheme(String theme) {
        this.theme = theme;
        // Aquí podrías cambiar estilos o colores si haces una versión DARK.
    }

    @Override
    public void setTimestamp(boolean includeTimestamp) {
        this.includeTimestamp = includeTimestamp;
        if (includeTimestamp) {
            try {
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Paragraph p = new Paragraph("Fecha de emisión: " + time, new Font(Font.FontFamily.HELVETICA, 10));
                p.setSpacingAfter(15);
                document.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setFooter(String footerMessage) {
        this.footerMessage = footerMessage;
        if (footerMessage != null && !footerMessage.isEmpty()) {
            try {
                Paragraph p = new Paragraph(footerMessage, new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC));
                p.setAlignment(Element.ALIGN_CENTER);
                p.setSpacingBefore(20);
                document.add(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public byte[] build() {
        document.close();
        return outputStream.toByteArray();
    }
}
