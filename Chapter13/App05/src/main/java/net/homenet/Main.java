package net.homenet;

import net.homenet.configuration.MailConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MailConfiguration.class);
        ErrorNotifier notifier = context.getBean(ErrorNotifier.class);
        notifier.notifyCopyError("C:\\Users\\Lee SangDae\\docs"
            , "C:\\Users\\Lee SangDae\\docs_backup"
            , "Spring5Recipes.doc");
    }
}
