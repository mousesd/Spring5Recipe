package net.homenet.s1;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

class CheckoutEvent extends ApplicationEvent {
    private final ShoppingCart cart;
    private final Date time;

    public CheckoutEvent(ShoppingCart cart, Date time) {
        super(cart);
        this.cart = cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Date getTime() {
        return time;
    }
}
