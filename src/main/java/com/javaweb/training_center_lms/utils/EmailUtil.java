package com.javaweb.training_center_lms.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailUtil {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("email");

    private final String host = resourceBundle.getString("SMTP_HOST");
    private final String sender_username = resourceBundle.getString("HOST_USERNAME");       // pw: project123456789
    private final String sender_password = resourceBundle.getString("HOST_PASSWORD");
    private final String TLS_port = resourceBundle.getString("TLS_PORT");

    private final Properties prop;
    private final ExecutorService executor;

    public EmailUtil() {
        prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", TLS_port);                // TSL: 587, SSL: 465
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", host);

        this.executor = Executors.newSingleThreadExecutor();
    }

    public void hostSendMailToUser(String receiver_email, String subject, String body) {
        executor.submit(() -> {
            try {
                // Phiên làm việc của gửi email
                Session session = Session.getInstance(prop, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sender_username, sender_password);
                    }
                });

                // Tạo một tin nhắn
                Message message = new MimeMessage(session);
                // Sender
                message.setFrom(new InternetAddress(sender_username));
                // Receiver
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver_email));

                // Quy định kiểu gửi (HTML)
                message.addHeader("Content-Type", "text/html; charset=utf-8");
                // Quy định địa chỉ email nhận phản hồi
                message.setReplyTo(InternetAddress.parse(sender_username));

                // Tiêu đề Email
                message.setSubject(subject);

                // Nội dung
                message.setContent(body, "text/html; charset=utf-8");

                // Sending email
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        String to = "taducduy2003@gmail.com";
        EmailUtil emailUtil = new EmailUtil();
        String message = "<!DOCTYPE html>\n" +
                "<html lang=\"vi\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Trang Web Đơn Giản</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Xin chào!</h1>\n" +
                "    <p>Đây là một trang web HTML đơn giản.</p>\n" +
                "</body>\n" +
                "</html>";
        emailUtil.hostSendMailToUser(to, "SUBJECT", message);
        System.out.println("send mail success!");
    }
}
