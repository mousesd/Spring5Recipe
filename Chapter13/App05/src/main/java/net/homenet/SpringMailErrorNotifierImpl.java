package net.homenet;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Objects;

public class SpringMailErrorNotifierImpl implements ErrorNotifier {
    private final MailSender mailSender;
    private final SimpleMailMessage copyErrorMailMessage;

    public SpringMailErrorNotifierImpl(MailSender mailSender, SimpleMailMessage copyErrorMailMessage) {
        this.mailSender = mailSender;
        this.copyErrorMailMessage = copyErrorMailMessage;
    }

    @Override
    public void notifyCopyError(String srcDir, String destDir, String filename) {
        SimpleMailMessage message = new SimpleMailMessage(copyErrorMailMessage);
        message.setText(String.format(Objects.requireNonNull(copyErrorMailMessage.getText()), srcDir, destDir, filename));
        mailSender.send(message);
    }
}
