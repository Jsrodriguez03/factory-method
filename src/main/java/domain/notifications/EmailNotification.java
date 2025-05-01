package domain.notifications;

import java.util.List;

public class EmailNotification implements Notification {
    private final String to;
    private final String subject;
    private final String body;
    private final List<String> cc;
    private final List<String> bcc;
    private final List<String> attachments;
    private final String priority;

    public EmailNotification(String to, String subject, String body, List<String> cc, List<String> bcc,
                             List<String> attachments, String priority) {
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.cc = cc;
        this.bcc = bcc;
        this.attachments = attachments;
        this.priority = priority;
    }

    @Override
    public void notifyUser() {
        System.out.println("📧 Email enviado a: " + to);
        System.out.println("Asunto: " + subject);
        System.out.println("Mensaje: " + body);
        if (cc != null) System.out.println("CC: " + cc);
        if (bcc != null) System.out.println("BCC: " + bcc);
        if (attachments != null) System.out.println("Adjuntos: " + attachments);
        System.out.println("Prioridad: " + priority);
    }
}
