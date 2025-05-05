package domain.builder.Report;

public class ReportDirector {
    private final ReportBuilder builder;

    public ReportDirector(ReportBuilder builder) {
        this.builder = builder;
    }

    public void construirReporte(domain.dto.PDFReportRequest request) {
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
