package net.homenet.s1;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
class ShoppingCart {
    private final List<Product> products = new ArrayList<>();

    void addItem(Product product) {
        products.add(product);
    }

    List<Product> getItems() {
        return products;
    }
}
