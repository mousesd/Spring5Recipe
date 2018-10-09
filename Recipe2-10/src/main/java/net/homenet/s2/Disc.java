package net.homenet.s2;

@SuppressWarnings({"unused", "SameParameterValue"})
class Disc extends Product {
    private int capacity;

    int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    Disc(String name, double price) {
        this(name, price, 0);
    }

    private Disc(String name, double price, double discount) {
        super(name, price, discount);
    }
}
