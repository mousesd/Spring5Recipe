package net.homenet.s3;

import java.text.DecimalFormat;

public abstract class Product {
    private final String name;
    private double price;
    private final double discount;

    double getPrice() {
        return price;
    }

    void setPrice(double price) {
        this.price = price;
    }

    Product(String name, double price) {
        this(name, price, 0);
    }

    Product(String name, double price, double discount) {
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#%");
        return name + " " + price + " (" + formatter.format(discount) + " discount!)";
    }
}
