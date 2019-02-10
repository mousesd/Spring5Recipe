package net.homenet.batch;

import net.homenet.domain.User;
import net.homenet.service.UserService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;

public class RetryableUserRegistrationItemWriter implements ItemWriter<User> {
    private final UserService userService;
    private final RetryTemplate retryTemplate;

    public RetryableUserRegistrationItemWriter(UserService userService, RetryTemplate retryTemplate) {
        this.userService = userService;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public void write(List<? extends User> items) {
        for (User user : items) {
            retryTemplate.execute(context -> userService.registerUser(user));
        }
    }
}
