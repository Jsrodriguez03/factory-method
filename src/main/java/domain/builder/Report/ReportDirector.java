package domain.builder.Report;

import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class ReportDirector {
    private final ReportBuilder builder;

    public ReportDirector(ReportBuilder builder) {
        this.builder = builder;
    }

    public void construirReporte(domain.dto.PDFReportRequest request) throws DocumentException, IOException {
        builder.setTheme(request.getTheme());
        builder.setFormat(request.getFormat());

        builder.reset();

        builder.setLogo(request.isIncludeLogo());
        builder.setTitle(request.getTitle());
        builder.setTimestamp(request.isIncludeTimestamp());
        builder.setUserInfo(request.isIncludeUserInfo());
        builder.setPaymentDetails(request.isIncludePaymentDetails());

        builder.setFooter(request.getFooterMessage());
    }
}
