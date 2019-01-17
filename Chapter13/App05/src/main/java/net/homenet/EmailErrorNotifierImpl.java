package net.homenet;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailErrorNotifierImpl implements ErrorNotifier {
    @Override
    public void notifyCopyError(String srcDir, String destDir, String filename) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mousesd@gmail.com", "*****");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mousesd@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("mousesd@gmail.com"));
            message.setSubject("File copy error");
            message.setText("Dear Administrator,\n\n"
                + "An error occurred when copying the following file:\n"
                + "Source directory: " + srcDir + "\n"
                + "Destination directory: " + destDir + "\n"
                + "Filename: " + filename);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
