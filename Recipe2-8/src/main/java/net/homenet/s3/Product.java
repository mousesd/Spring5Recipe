package net.homenet.s3;

import java.text.DecimalFormat;

public abstract class Product {
    private String name;
    private double price;
    private double discount;

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
