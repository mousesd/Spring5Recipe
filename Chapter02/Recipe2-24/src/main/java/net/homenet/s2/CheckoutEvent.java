package net.homenet.s2;

import java.util.Date;

class CheckoutEvent  {
    private final ShoppingCart cart;
    private final Date time;

    CheckoutEvent(ShoppingCart cart, Date time) {
        this.cart = cart;
        this.time = time;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    Date getTime() {
        return time;
    }
}
