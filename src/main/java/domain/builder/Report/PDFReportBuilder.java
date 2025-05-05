package domain.builder.Report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
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

    private BaseColor backgroundColor;
    private BaseColor textColor;
    private BaseColor separatorColor;
    private boolean isDarkTheme;

    @Override
    public void reset() {
        outputStream = new ByteArrayOutputStream();
        Rectangle pageSize = "LETTER".equalsIgnoreCase(format) ? PageSize.LETTER : PageSize.A4;

        // Theme-based color settings
        if (isDarkTheme) {
            backgroundColor = BaseColor.BLACK;
            textColor = BaseColor.WHITE;
            separatorColor = BaseColor.LIGHT_GRAY;
        } else {
            backgroundColor = BaseColor.WHITE;
            textColor = BaseColor.BLACK;
            separatorColor = BaseColor.DARK_GRAY;
        }


        document = new Document(pageSize, 50, 50, 50, 50);
        try {
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            if ("dark".equalsIgnoreCase(theme)) {
                // Add black rectangle as background
                PdfContentByte canvas = writer.getDirectContentUnder();
                canvas.setColorFill(backgroundColor);
                canvas.rectangle(0, 0, pageSize.getWidth(), pageSize.getHeight());
                canvas.fill();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTheme(String theme) {
        this.theme = theme;
        this.isDarkTheme = "dark".equalsIgnoreCase(theme);
    }

    @Override
    public void setLogo(boolean includeLogo) {
        this.includeLogo = includeLogo;
        if (includeLogo) {
            try {
                Image img = Image.getInstance("classpath:logo.png");
                img.scaleToFit(80, 80);
                img.setAlignment(Image.ALIGN_RIGHT);
                document.add(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
        document.addTitle(title);
        try {
            Paragraph p = new Paragraph(title.toUpperCase(),
                    new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, textColor));
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(5);
            document.add(p);

            LineSeparator separator = new LineSeparator();
            separator.setLineColor(separatorColor);
            document.add(new Chunk(separator));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setPaymentDetails(boolean includePaymentDetails) {
        this.includePaymentDetails = includePaymentDetails;

        if (includePaymentDetails) {
            try {
                Paragraph sectionTitle = new Paragraph("Detalles del Pago",
                        new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, textColor));
                sectionTitle.setSpacingBefore(15);
                sectionTitle.setSpacingAfter(10);
                document.add(sectionTitle);

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingAfter(15);
                table.setWidths(new int[]{1, 2});

                addPaymentRow(table, "Método de Pago:", paymentType);
                addPaymentRow(table, "Monto Inicial:", "$" + paymentAmount + " USD");
                addPaymentRow(table, "Impuesto:", "$" + paymentTax + " USD");
                addPaymentRow(table, "Total a Pagar:", "$" + paymentTotal + " USD");

                document.add(table);

                LineSeparator separator = new LineSeparator();
                separator.setLineColor(separatorColor);
                document.add(new Chunk(separator));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addPaymentRow(PdfPTable table, String label, String value) {
        Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, textColor);
        Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, textColor);

        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));

        labelCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setBackgroundColor(backgroundColor);
        valueCell.setBackgroundColor(backgroundColor);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    @Override
    public void setUserInfo(boolean includeUserInfo) {
        this.includeUserInfo = includeUserInfo;
        if (includeUserInfo) {
            try {
                Paragraph sectionTitle = new Paragraph("Información del Usuario",
                        new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, textColor));
                sectionTitle.setSpacingBefore(15);
                sectionTitle.setSpacingAfter(10);
                document.add(sectionTitle);

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setWidths(new int[]{1, 2});
                table.setSpacingAfter(15);

                addPaymentRow(table, "Nombre:", "Santiago Rodriguez");
                addPaymentRow(table, "Email:", "santiago@gmail.com");

                document.add(table);

                LineSeparator separator = new LineSeparator();
                separator.setLineColor(separatorColor);
                document.add(new Chunk(separator));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setTimestamp(boolean includeTimestamp) {
        this.includeTimestamp = includeTimestamp;
        System.out.println("setTimestamp called: " + includeTimestamp); // <-- DEBUG
        if (!includeTimestamp) {
            try {
                String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                Paragraph p = new Paragraph("Fecha de emisión: " + time,
                        new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, textColor));
                p.setSpacingBefore(10);
                p.setSpacingAfter(15);
                p.setAlignment(Element.ALIGN_RIGHT);
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
                LineSeparator separator = new LineSeparator();
                separator.setLineColor(separatorColor);
                document.add(new Chunk(separator));

                Paragraph p = new Paragraph(footerMessage,
                        new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, textColor));
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
