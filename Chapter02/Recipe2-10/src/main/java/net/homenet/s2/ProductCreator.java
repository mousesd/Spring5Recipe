package net.homenet.s2;

import java.util.Map;

class ProductCreator {
    private Map<String, Product> products;

    void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    Product createProduct(String productId) {
        Product product = products.get(productId);
        if (product != null) {
            return product;
        }
        throw new IllegalArgumentException("Unknown product");
    }
}
