package domain.builder.Report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
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

    private String paymentType;
    private String paymentAmount;
    private String paymentTotal;
    private String paymentTax;

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
                Paragraph p = new Paragraph();
                p.setFont(new Font(Font.FontFamily.HELVETICA, 12));

                p.add("Detalles del Pago:\n\n");
                p.add("- Método de Pago: " + paymentType + "\n");
                p.add("- Monto Inicial: $" + paymentAmount + " USD\n");
                p.add("- Impuesto: $" + paymentTax + " USD\n");
                p.add("- Total a Pagar: $" + paymentTotal + " USD\n");

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
                Paragraph p = new Paragraph("Usuario:\n- Nombre: Santiago Rodriguez\n- Email: santiago@gmail.com",
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
        // Aquí podrías personalizar estilos para temas como "DARK", etc.
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
