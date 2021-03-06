package net.homenet.s1;

import java.text.DecimalFormat;

public abstract class Product {
    private final String name;
    private final double price;
    private final double discount;

    public String getName() {
        return name;
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
