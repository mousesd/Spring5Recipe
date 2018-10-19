package net.homenet.s1;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener implements ApplicationListener<CheckoutEvent> {
    @Override
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.printf("Checkout event [%s]\n", event.getTime());
    }
}
