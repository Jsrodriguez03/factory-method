package domain.builder.Report;

public class ReportDirector {
    private final ReportBuilder builder;

    public ReportDirector(ReportBuilder builder) {
        this.builder = builder;
        builder.reset();
    }

    public void construirReporte(domain.dto.PDFReportRequest request) {
        builder.setFormat(request.getFormat());
        builder.setLogo(request.isIncludeLogo());
        builder.setTitle(request.getTitle());
        builder.setUserInfo(request.isIncludeUserInfo());
        builder.setPaymentDetails(request.isIncludePaymentDetails());
        builder.setTheme(request.getTheme());
        builder.setTimestamp(request.isIncludeTimestamp());
        builder.setFooter(request.getFooterMessage());
    }
}
