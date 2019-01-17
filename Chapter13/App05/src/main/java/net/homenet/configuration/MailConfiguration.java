package net.homenet.configuration;

import net.homenet.ErrorNotifier;
import net.homenet.SpringMailErrorNotifierImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
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
    public MailSender mailSender() {
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
    public ErrorNotifier errorNotifier(MailSender mailSender) {
        return new SpringMailErrorNotifierImpl(mailSender);
    }
}
