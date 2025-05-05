package domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PDFReportRequest {
    private String title;
    private boolean includeLogo;
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
}
