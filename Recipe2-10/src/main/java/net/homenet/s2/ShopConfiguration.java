package net.homenet.s2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan("net.homenet.s2")
public class ShopConfiguration {

    @Bean
    public ProductCreator productCreator() {
        Map<String, Product> products = new HashMap<>();
        products.put("aaa", new Battery("AAA", 2.5));
        products.put("cdrw", new Disc("CD-RW", 1.5));
        products.put("dvdrw", new Disc("DVD-RW", 3.0));

        ProductCreator creator = new ProductCreator();
        creator.setProducts(products);
        return creator;
    }

    @Bean
    public Product battery() {
        return productCreator().createProduct("aaa");
    }

    @Bean
    public Product cdrw() {
        return productCreator().createProduct("cdrw");
    }

    @Bean
    public Product dvdrw() {
        return productCreator().createProduct("dvdrw");
    }
}
