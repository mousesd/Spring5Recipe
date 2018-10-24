package net.homenet.s2;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener {
    @EventListener
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.printf("Checkout event [%s]\n", event.getTime());
    }
}
