package net.homenet;

public class Disc extends Product {
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    Disc(String name, double price, double discount) {
        super(name, price, discount);
    }
}
