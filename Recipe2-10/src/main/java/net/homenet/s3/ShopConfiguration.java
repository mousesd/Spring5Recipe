package net.homenet.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.homenet.s3")
public class ShopConfiguration {

    @Bean
    public Product battery() {
        return new Battery("AAA", 2.5);
    }

    @Bean
    public Product cdrw() {
        return new Disc("CD-RW", 1.5);
    }

    @Bean
    public Product dvdrw() {
        return new Disc("DVD-RW", 3.0);
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanAaa() {
        DiscountFactoryBean discountAaa = new DiscountFactoryBean();
        discountAaa.setProduct(new Battery("AAA", 2.5));
        discountAaa.setDiscount(0.2);
        return discountAaa;
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanCdRw() {
        DiscountFactoryBean discountCdRw = new DiscountFactoryBean();
        discountCdRw.setProduct(new Disc("CD-RW", 1.5));
        discountCdRw.setDiscount(0.1);
        return discountCdRw;
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanDvdRw() {
        DiscountFactoryBean discountDvdRw = new DiscountFactoryBean();
        discountDvdRw.setProduct(new Disc("DVD-RW", 3.0));
        discountDvdRw.setDiscount(0.1);
        return discountDvdRw;
    }
}
