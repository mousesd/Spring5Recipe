package net.homenet.configuration;

import net.homenet.ErrorNotifier;
import net.homenet.SpringMailErrorNotifierImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {
    //# 1.Use a JavaMail API
    //@Bean
    //public ErrorNotifier errorNotifier() {
    //    return new EmailErrorNotifierImpl();
    //}

    //# 2.Use a Spring's MailSender
    @Bean
    public JavaMailSender mailSender() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setJavaMailProperties(props);
        mailSender.setUsername("mousesd@gmail.com");
        mailSender.setPassword("******");
        return mailSender;
    }

    @Bean
    public SimpleMailMessage copyErrorMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mousesd@gmail.com");
        message.setTo("mousesd@gmail.com");
        message.setSubject("File copy error");
        message.setText("Dear Administrator,\n\n"
            + "An error occurred when copying the following file:\n"
            + "Source directory: %s\n"
            + "Destination directory: %s\n"
            + "Filename: %s");
        return message;
    }

    @Bean
    public ErrorNotifier errorNotifier(JavaMailSender mailSender, SimpleMailMessage copyErrorMailMessage) {
        return new SpringMailErrorNotifierImpl(mailSender, copyErrorMailMessage);
    }
}
