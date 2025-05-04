package domain.builder.Report;

public interface ReportBuilder {
    void reset();
    void setTitle(String title);
    void setLogo(boolean includeLogo);
    void setPaymentDetails(boolean includePaymentDetails);
    void setUserInfo(boolean includeUserInfo);
    void setTheme(String theme);
    void setTimestamp(boolean includeTimestamp);
    void setFooter(String footerMessage);
    void setFormat(String format);

    byte[] build(); // Devuelve el PDF como byte[]
}
