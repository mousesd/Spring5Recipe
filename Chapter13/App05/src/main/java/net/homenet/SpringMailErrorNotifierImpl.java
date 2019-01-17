package net.homenet;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class SpringMailErrorNotifierImpl implements ErrorNotifier {
    private final JavaMailSender mailSender;
    private final SimpleMailMessage copyErrorMailMessage;

    public SpringMailErrorNotifierImpl(JavaMailSender mailSender, SimpleMailMessage copyErrorMailMessage) {
        this.mailSender = mailSender;
        this.copyErrorMailMessage = copyErrorMailMessage;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void notifyCopyError(String srcDir, String destDir, String filename) {
        //# 1.
        //SimpleMailMessage message = new SimpleMailMessage(copyErrorMailMessage);
        //message.setText(String.format(Objects.requireNonNull(copyErrorMailMessage.getText()), srcDir, destDir, filename));
        //mailSender.send(message);

        //# 2.Send email with attachments I
        //MimeMessage message = mailSender.createMimeMessage();
        //try {
        //    MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //    helper.setFrom(copyErrorMailMessage.getFrom());
        //    helper.setTo(copyErrorMailMessage.getTo());
        //    helper.setSubject(copyErrorMailMessage.getSubject());
        //    helper.setText(String.format(copyErrorMailMessage.getText(), srcDir, destDir, filename));
        //    helper.addAttachment("logback.xml", new ClassPathResource("logback.xml"));
        //} catch (MessagingException e) {
        //    e.printStackTrace();
        //}
        //mailSender.send(message);

        //# 3.Send email with attachments II
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(copyErrorMailMessage.getFrom());
            helper.setTo(copyErrorMailMessage.getTo());
            helper.setSubject(copyErrorMailMessage.getSubject());
            helper.setText(String.format(copyErrorMailMessage.getText(), srcDir, destDir, filename));
            helper.addAttachment("logback.xml", new ClassPathResource("logback.xml"));
        };
        mailSender.send(preparator);
    }
}
