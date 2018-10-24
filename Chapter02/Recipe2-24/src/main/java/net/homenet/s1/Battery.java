package net.homenet.s1;

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
        super(name, price);
    }
}
