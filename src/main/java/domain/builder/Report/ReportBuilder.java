package domain.builder.Report;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface ReportBuilder extends Cloneable {
    PDFReportBuilder clone();

    void reset();
    void setTitle(String title);
    void setLogo(boolean includeLogo) throws DocumentException, IOException;
    void setPaymentDetails(boolean includePaymentDetails);
    void setUserInfo(boolean includeUserInfo);
    void setTheme(String theme);
    void setTimestamp(boolean includeTimestamp);
    void setFooter(String footerMessage);
    void setFormat(String format);

    byte[] build(); // Devuelve el PDF como byte[]
}


