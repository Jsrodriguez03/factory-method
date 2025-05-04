package domain.factory.notifications;

import domain.builder.Notifications.EmailNotificationBuilder;
import domain.notifications.Notification;

import java.util.List;

public class EmailNotificationFactory extends NotificationFactory {
    private final String to;
    private final String subject;
    private final String body;
    private final List<String> cc;
    private final List<String> bcc;
    private final List<String> attachments;
    private final String priority;

    public EmailNotificationFactory(String to, String subject, String body, List<String> cc,
                                    List<String> bcc, List<String> attachments, String priority) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.cc = cc;
        this.bcc = bcc;
        this.attachments = attachments;
        this.priority = priority;
    }

    @Override
    public Notification createNotification() {
        return new EmailNotificationBuilder()
                .setTo(to)
                .setSubject(subject)
                .setBody(body)
                .setCc(cc)
                .setBcc(bcc)
                .setAttachments(attachments)
                .setPriority(priority)
                .build();
    }
}
