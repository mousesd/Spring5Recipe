package net.homenet;

public class Disc extends Product {
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Disc(String name, double price) {
        super(name, price);
    }
}
