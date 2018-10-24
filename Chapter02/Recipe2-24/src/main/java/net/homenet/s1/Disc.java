package net.homenet.s1;

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
        super(name, price);
    }
}
