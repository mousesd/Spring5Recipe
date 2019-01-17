package net.homenet;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SpringMailErrorNotifierImpl implements ErrorNotifier {
    private final MailSender mailSender;

    public SpringMailErrorNotifierImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void notifyCopyError(String srcDir, String destDir, String filename) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("mousesd@gmail.com");
        message.setTo("mousesd@gmail.com");
        message.setSubject("File copy error");
        message.setText("Dear Administrator,\n\n"
            + "An error occurred when copying the following file:\n"
            + "Source directory: " + srcDir + "\n"
            + "Destination directory: " + destDir + "\n"
            + "Filename: " + filename);
        mailSender.send(message);
    }
}
