package net.homenet.s2;

public class Battery extends Product {
    private boolean rechargeable;

    public boolean getRechargeable() {
        return rechargeable;
    }

    void setRechargeable(boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

    Battery(String name, double price, double discount) {
        super(name, price, discount);
    }
}
