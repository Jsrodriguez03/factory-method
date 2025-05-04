package domain.builder.Report;

import domain.DTOs.ReportConfig;

public class ReportDirector {
    private final ReportBuilder builder;

    public ReportDirector(ReportBuilder builder) {
        this.builder = builder;
    }

    public byte[] constructReport(ReportConfig config) {
        builder.setFormat(config.format);
        builder.reset();
        builder.setLogo(config.includeLogo);
        builder.setTitle(config.title);
        builder.setPaymentDetails(config.includePaymentDetails);
        builder.setUserInfo(config.includeUserInfo);
        builder.setTheme(config.theme);
        builder.setTimestamp(config.includeTimestamp);
        builder.setFooter(config.footerMessage);
        return builder.build();
    }
}
