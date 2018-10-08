package net.homenet.s2;

@SuppressWarnings({"unused", "SameParameterValue"})
class Battery extends Product {
    private boolean rechargeable;

    boolean isRechargeable() {
        return rechargeable;
    }

    void setRechargeable(boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

    Battery(String name, double price) {
        this(name, price, 0);
    }

    private Battery(String name, double price, double discount) {
        super(name, price, discount);
    }
}
