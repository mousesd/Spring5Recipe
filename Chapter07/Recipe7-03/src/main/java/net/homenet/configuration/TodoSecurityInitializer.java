package net.homenet.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class TodoSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    public TodoSecurityInitializer() {
        super(TodoSecurityConfiguration.class);
    }
}
