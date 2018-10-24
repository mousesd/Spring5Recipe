package net.homenet.s1;

class ProductCreator {
    static Product createProduct(String productId) {
        switch (productId) {
            case "aaa":
                return new Battery("AAA", 2.5);
            case "cdrw":
                return new Disc("CD-RW", 1.5);
            case "dvdrw":
                return new Disc("DVD-RW", 3.0);
        }
        throw new IllegalArgumentException("Unknown product");
    }
}
