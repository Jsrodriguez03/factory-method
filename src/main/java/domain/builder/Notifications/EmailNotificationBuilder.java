package domain.builder.Notifications;

import domain.notifications.EmailNotification;
import java.util.List;

public class EmailNotificationBuilder {

    private String to;
    private String subject;
    private String body;
    private List<String> cc;
    private List<String> bcc;
    private List<String> attachments;
    private String priority;

    public static EmailNotificationBuilder builder() {
        return new EmailNotificationBuilder();
    }

    public EmailNotificationBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public EmailNotificationBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailNotificationBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public EmailNotificationBuilder setCc(List<String> cc) {
        this.cc = cc;
        return this;
    }

    public EmailNotificationBuilder setBcc(List<String> bcc) {
        this.bcc = bcc;
        return this;
    }

    public EmailNotificationBuilder setAttachments(List<String> attachments) {
        this.attachments = attachments;
        return this;
    }

    public EmailNotificationBuilder setPriority(String priority) {
        this.priority = priority;
        return this;
    }

    public EmailNotification build() {
        return new EmailNotification(to, subject, body, cc, bcc, attachments, priority);
    }
}
